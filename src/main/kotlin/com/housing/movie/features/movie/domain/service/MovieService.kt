package com.housing.movie.features.movie.domain.service

import com.housing.movie.features.movie.domain.entity.Movie

interface MovieService {
    fun getAllMovies(): List<Movie>

    fun getMovieByTitle(title: String): Movie
}