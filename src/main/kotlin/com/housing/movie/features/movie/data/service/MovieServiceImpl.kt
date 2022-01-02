package com.housing.movie.features.movie.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectDisabledException
import com.housing.movie.features.genre.data.repository.GenreRepository
import com.housing.movie.features.genre.data.service.GenreServiceImpl
import com.housing.movie.features.movie.data.repository.MovieDetailRepository
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.entity.MovieDetail
import com.housing.movie.features.movie.domain.service.MovieService
import com.housing.movie.features.movie.domain.usecase.create_movie.CreateMovieRequest
import com.housing.movie.features.movie.domain.usecase.update_movie.UpdateMovieRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository,
    private val movieDetailRepository: MovieDetailRepository,
) : MovieService {

    companion object {
        const val MOVIE_NOT_FOUND = "Movie not found"
        const val MOVIE_REMOVED = "Movie removed"
    }

    override fun getAllMovies(): List<Movie> {
        return movieRepository.getAllByEnabledIsTrue()
    }

    override fun getMovieByTitle(title: String): Movie {

        return movieRepository.getMovieByTitleAndEnabledIsTrue(title) ?: throw NotFoundException(MOVIE_NOT_FOUND)
    }

    override fun getMovieById(id: UUID): Movie {

        return movieRepository.getMovieById(id) ?: throw NotFoundException(MOVIE_NOT_FOUND)

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

    @Transactional
    override fun deleteMovie(id: UUID): Boolean {
        val movie = movieRepository.findByIdOrNull(id) ?: throw NotFoundException(MOVIE_NOT_FOUND)

        movie.enabled = false

        return true
    }

    @Transactional
    override fun enableMovie(id: UUID): Movie {
        val movie = movieRepository.findByIdOrNull(id) ?: throw NotFoundException(MOVIE_NOT_FOUND)

        movie.enabled = true

        return movie
    }

    override fun createMovie(createMovieRequest: CreateMovieRequest): Movie {
        val movieDetail = createMovieRequest.toMovieDetail()

        val genres = genreRepository.getGenresByIdIsIn(createMovieRequest.genreIds)

        val movie = Movie()

        movie.id = createMovieRequest.id ?: movie.id
        movie.title = movieDetail.title
        movie.movieDetail = movieDetail
        movie.genres = movie.genres.plus(genres)

        movieRepository.save(movie)

        return movie
    }

    @Transactional
    override fun updateMovie(updateMovieRequest: UpdateMovieRequest): Movie {
        val movie: Movie = movieRepository.findByIdOrNull(updateMovieRequest.id) ?: throw NotFoundException(
            MOVIE_NOT_FOUND
        )

        val newMovieDetail = updateMovieRequest.toMovieDetail(movie.movieDetail)

        movie.title = newMovieDetail.title

        if (updateMovieRequest.genreIds != null) {
            movie.genres = genreRepository.getGenresByIdIsIn(updateMovieRequest.genreIds)
        }

        movieRepository.save(movie)
        movieDetailRepository.save(newMovieDetail)

        return movie
    }
}