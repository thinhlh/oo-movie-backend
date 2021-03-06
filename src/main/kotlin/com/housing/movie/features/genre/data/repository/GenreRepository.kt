package com.housing.movie.features.genre.data.repository

import com.housing.movie.features.genre.domain.entity.Genre
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GenreRepository : CrudRepository<Genre, UUID> {
    fun existsByName(name: String): Boolean

    fun getGenresByIdIsIn(ids: List<UUID>): List<Genre>

}