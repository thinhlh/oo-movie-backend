package com.housing.movie.features.movie.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectDisabledException
import com.housing.movie.features.genre.data.repository.GenreRepository
import com.housing.movie.features.genre.data.service.GenreServiceImpl
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository,
) : MovieService {

    companion object {
        const val MOVIE_NOT_FOUND = "Movie Not Found"
        const val MOVIE_REMOVED = "Movie Removed"
    }

    override fun getAllMovies(): List<Movie> {
        return movieRepository.getAllByEnabledIsTrue()
    }

    override fun getMovieByTitle(title: String): Movie {

        return movieRepository.getMovieByTitleAndEnabledIsTrue(title) ?: throw NotFoundException(MOVIE_NOT_FOUND)
    }

    override fun getMovieById(id: UUID): Movie {

        return movieRepository.getMoviesByIdAndEnabledIsTrue(id) ?: throw NotFoundException(MOVIE_NOT_FOUND)

    }

    @Transactional
    override fun assignGenresToMovie(movieId: UUID, genreIds: List<UUID>): Movie {

        // Get movie that are not disabled
        val movie = movieRepository.findByIdOrNull(movieId) ?: throw NotFoundException(MOVIE_NOT_FOUND)

        val genres = genreRepository.findAllById(genreIds)

        movie.genres = genres.toList()

        return movie

    }

    override fun getMoviesByGenre(genreId: UUID): List<Movie> {
        val genre = genreRepository.findByIdOrNull(genreId) ?: throw NotFoundException(GenreServiceImpl.GENRE_NOT_FOUND)

        if (!genre.enabled) throw ObjectDisabledException(GenreServiceImpl.GENRE_NOT_EXISTS)

        return movieRepository.findMoviesByEnabledIsTrueAndGenresContaining(genre)

    }
}