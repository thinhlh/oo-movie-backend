package com.housing.movie.features.movie.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectDisabledException
import com.housing.movie.features.genre.data.repository.GenreRepository
import com.housing.movie.features.genre.data.service.GenreServiceImpl
import com.housing.movie.features.movie.data.repository.MovieDetailRepository
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import com.housing.movie.features.movie.domain.usecase.create_movie.CreateMovieRequest
import com.housing.movie.features.movie.domain.usecase.rating_movie.RatingMovieRequest
import com.housing.movie.features.movie.domain.usecase.update_movie.UpdateMovieRequest
import org.springframework.data.domain.Pageable
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

    @Transactional
    override fun getAllMovies(): List<Movie> {
        return movieRepository.findAll().toList()
    }

    @Transactional
    override fun getMovieByTitle(title: String): Movie {
        return movieRepository.getMovieByTitleAndEnabledIsTrue(title) ?: throw NotFoundException(MOVIE_NOT_FOUND)
    }

    @Transactional
    override fun getMovieById(id: UUID): Movie {
        return movieRepository.findByIdOrNull(id) ?: throw NotFoundException(MOVIE_NOT_FOUND)
    }

    @Transactional
    override fun assignGenresToMovie(movieId: UUID, genreIds: List<UUID>): Movie {

        // Get movie that are not disabled
        val movie = movieRepository.findByIdOrNull(movieId) ?: throw NotFoundException(MOVIE_NOT_FOUND)

        val genres = genreRepository.findAllById(genreIds)

        movie.genres = genres.toList()

        return movie

    }

    @Transactional
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

        var movie = Movie()

        movie.id = createMovieRequest.id ?: movie.id
        movie.title = movieDetail.title
        movie.movieDetail = movieDetail
        movie.genres = movie.genres.plus(genres)
        movie.movieIdFake = createMovieRequest.movieIdFake

        movie = movieRepository.save(movie)

        return movie
    }

    @Transactional
    override fun updateMovie(updateMovieRequest: UpdateMovieRequest): Movie {
        val movie: Movie = movieRepository.findByIdOrNull(updateMovieRequest.id) ?: throw NotFoundException(
            MOVIE_NOT_FOUND
        )

        val newMovieDetail = updateMovieRequest.toMovieDetail(movie.movieDetail)

        movie.title = newMovieDetail.title
        movie.movieIdFake = updateMovieRequest.movieIdFake ?: movie.movieIdFake

        if (updateMovieRequest.genreIds != null) {
            movie.genres = genreRepository.getGenresByIdIsIn(updateMovieRequest.genreIds)
        }

        movieRepository.save(movie)
        movieDetailRepository.save(newMovieDetail)

        return movie
    }

    override fun ratingMovie(ratingMovieRequest: RatingMovieRequest): Double {
        val movieId = ratingMovieRequest.movieId
        val voteValue = ratingMovieRequest.value

        val movieDetail = movieDetailRepository.findByMovie_Id(movieId) ?: throw NotFoundException(MOVIE_NOT_FOUND)

        // Calculate the total vote then plus the new vote value and get the average
        val totalCurrentVoteValue = movieDetail.voteAverage * movieDetail.voteCount

        movieDetail.voteCount += 1
        movieDetail.voteAverage = (totalCurrentVoteValue + voteValue) / movieDetail.voteCount

        movieDetailRepository.save(movieDetail)

        return movieDetail.voteAverage
    }

    override fun topMovieRating(size: Int): List<Movie?> {
        return movieDetailRepository.findByOrderByVoteAverageDesc(Pageable.ofSize(size)).map { it.movie }
    }
}