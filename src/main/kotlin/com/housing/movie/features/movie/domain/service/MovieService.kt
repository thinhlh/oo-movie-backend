package com.housing.movie.features.movie.domain.service

import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.usecase.create_movie.CreateMovieRequest
import com.housing.movie.features.movie.domain.usecase.rating_movie.RatingMovieRequest
import com.housing.movie.features.movie.domain.usecase.update_movie.UpdateMovieRequest
import java.util.*

interface MovieService {
    fun getAllMovies(): List<Movie>

    fun getMovieByTitle(title: String): Movie

    fun getMovieById(id: UUID): Movie

    fun assignGenresToMovie(movieId: UUID, genreIds: List<UUID>): Movie

    fun getMoviesByGenre(genreId: UUID): List<Movie>

    fun deleteMovie(id: UUID): Boolean

    fun enableMovie(id: UUID): Movie

    fun createMovie(createMovieRequest: CreateMovieRequest): Movie

    fun updateMovie(updateMovieRequest: UpdateMovieRequest): Movie

    fun ratingMovie(ratingMovieRequest: RatingMovieRequest): Double

    fun topMovieRating(size: Int): List<UUID>
}