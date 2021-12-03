package com.housing.movie.features.genre.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectAlreadyExistsException
import com.housing.movie.features.genre.data.repository.GenreRepository
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.service.GenreService
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class GenreServiceImpl(
    private val genreRepository: GenreRepository
) : GenreService {

    companion object {
        const val GENRE_TITLE_EXISTS = "There is another genre with title %s"
        const val GENRE_NOT_EXISTS = "The given genre has been removed"
        const val GENRE_NOT_FOUND = "There is no genre"
    }

    override fun getAllGenres(): List<Genre> {
        return genreRepository.getAllByEnabledIsTrue()
    }

    override fun createGenre(createGenreRequest: CreateGenreRequest): Genre {
        if (genreRepository.existsGenreByTitle(createGenreRequest.title)) {
            // Already exists
            throw ObjectAlreadyExistsException(message = GENRE_TITLE_EXISTS.format(createGenreRequest.title))
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
    override fun redoDeleteGenre(id: UUID): Boolean {
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
    override fun updateGenreTitle(id: UUID, title: String): Genre {
        val genre: Genre = checkGenreExists(genreRepository.getGenreById(id))
        if (genreRepository.existsGenreByTitle(title)) {
            // Already exists
            throw ObjectAlreadyExistsException(message = GENRE_TITLE_EXISTS.format(title))
        }
        genre.title = title

        return genre
    }

    private fun checkGenreExists(genre: Genre?): Genre {
        return genre ?: throw NotFoundException(GENRE_NOT_FOUND)
    }
}