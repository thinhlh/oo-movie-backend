package com.housing.movie.features.genre.domain.service

import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest

interface GenreService {
    fun getAllGenres(): List<Genre>

    fun createGenre(createGenreRequest: CreateGenreRequest): Genre
}