package com.housing.movie.features.movie.data.repository

import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.movie.domain.entity.Movie
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MovieRepository : CrudRepository<Movie, UUID> {

    fun getMovieByTitleAndEnabledIsTrue(title: String): Movie?

    fun findMoviesByEnabledIsTrueAndGenresContaining(genre: Genre): List<Movie>

    fun findByIdIsIn(ids: Collection<UUID>): List<Movie>

}