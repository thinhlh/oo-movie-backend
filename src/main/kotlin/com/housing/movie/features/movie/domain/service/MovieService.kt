package com.housing.movie.features.movie.domain.service

import com.housing.movie.features.movie.domain.entity.Movie
import java.util.*

interface MovieService {
    fun getAllMovies(): List<Movie>

    fun getMovieByTitle(title: String): Movie

    fun getMovieById(id: UUID): Movie

    fun assignGenresToMovie(movieId: UUID, genreIds: List<UUID>): Movie

    fun getMoviesByGenre(genreId: UUID): List<Movie>
}