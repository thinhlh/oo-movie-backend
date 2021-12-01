package com.housing.movie.features.genre.data.service

import com.housing.movie.exceptions.ObjectExistsException
import com.housing.movie.features.genre.data.repository.GenreRepository
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.service.GenreService
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(
        private val genreRepository: GenreRepository
) : GenreService {
    override fun getAllGenres(): List<Genre> {
        return genreRepository.getAllByEnabledIsTrue()
    }

    override fun createGenre(createGenreRequest: CreateGenreRequest): Genre {
        if (genreRepository.existsGenreByTitle(createGenreRequest.title)) {
            // Already exists
            throw ObjectExistsException(message = "There is another genre with title ${createGenreRequest.title}")
        } else {
            val genre = createGenreRequest.toGenre()
            genreRepository.save(genre)
            return genre
        }
    }

}