package com.housing.movie.features.genre.domain.service

import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import java.util.*

interface GenreService {
    fun getAllGenres(): List<Genre>

    fun createGenre(createGenreRequest: CreateGenreRequest): Genre

    fun deleteGenre(id: UUID): Boolean

    fun redoDeleteGenre(id: UUID): Boolean

    fun updateGenreTitle(id: UUID, title: String): Genre
}