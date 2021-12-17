package com.housing.movie.features.genre.data.repository

import com.housing.movie.features.genre.domain.entity.Genre
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GenreRepository : CrudRepository<Genre, UUID> {
    fun getAllByEnabledIsTrue(): List<Genre>

    fun existsGenreByName(title: String): Boolean

    fun getGenreById(id: UUID): Genre?

}