package com.housing.movie.features.movie.data.repository

import com.housing.movie.features.movie.domain.entity.Movie
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MovieRepository : CrudRepository<Movie, UUID> {

    fun getAllByEnabledIsTrue(): List<Movie>

    fun getMovieByTitle(title: String): Movie?

}