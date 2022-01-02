package com.housing.movie.features.genre.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectAlreadyExistsException
import com.housing.movie.features.genre.data.repository.GenreRepository
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.service.GenreService
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import com.housing.movie.features.genre.domain.usecase.update_genre.UpdateGenreRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class GenreServiceImpl(
    private val genreRepository: GenreRepository
) : GenreService {

    companion object {
        const val GENRE_NAME_EXISTS = "There is another genre with name %s"
        const val GENRE_NOT_EXISTS = "The given genre has been removed"
        const val GENRE_NOT_FOUND = "There is no genre"
    }

    override fun getAllGenres(): List<Genre> {
        return genreRepository.getAllByEnabledIsTrue()
    }

    override fun createGenre(createGenreRequest: CreateGenreRequest): Genre {
        if (genreRepository.existsByName(createGenreRequest.name)) {
            // Already exists
            throw ObjectAlreadyExistsException(message = GENRE_NAME_EXISTS.format(createGenreRequest.name))
        } else {
            val genre = createGenreRequest.toGenre()
            genreRepository.save(genre)
            return genre
        }
    }

    @Transactional
    override fun deleteGenre(id: UUID): Boolean {
        return updateGenreVisibility(id, false)
    }

    @Transactional
    override fun enableGenre(id: UUID): Boolean {
        return updateGenreVisibility(id, true)
    }

    @Transactional
    protected fun updateGenreVisibility(id: UUID, enabled: Boolean): Boolean {
        val genre: Genre = checkGenreExists(genreRepository.getGenreById(id))

        genre.apply {
            this.enabled = enabled
        }

        return true
    }

    @Transactional
    override fun updateGenre(updateGenreRequest: UpdateGenreRequest): Genre {
        val genre: Genre = checkGenreExists(genreRepository.getGenreById(updateGenreRequest.id))
        if (genreRepository.existsByName(updateGenreRequest.name)) {
            // Already exists
            throw ObjectAlreadyExistsException(message = GENRE_NAME_EXISTS.format(updateGenreRequest.name))
        }
        genre.name = updateGenreRequest.name
        genre.genreIdFake = updateGenreRequest.genreIdFake

        return genre
    }

    private fun checkGenreExists(genre: Genre?): Genre {
        return genre ?: throw NotFoundException(GENRE_NOT_FOUND)
    }
}