package com.housing.movie.features.genre.domain.service

import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import com.housing.movie.features.genre.domain.usecase.update_genre.UpdateGenreRequest
import java.util.*

interface GenreService {
    fun getAllGenres(): List<Genre>

    fun createGenre(createGenreRequest: CreateGenreRequest): Genre

    fun deleteGenre(id: UUID): Boolean

    fun enableGenre(id: UUID): Boolean

    fun updateGenre(updateGenreRequest: UpdateGenreRequest): Genre
}