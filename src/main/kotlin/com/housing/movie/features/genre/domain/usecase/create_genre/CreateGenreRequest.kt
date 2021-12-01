package com.housing.movie.features.genre.domain.usecase.create_genre

import com.housing.movie.features.genre.domain.entity.Genre
import javax.validation.constraints.NotNull


data class CreateGenreRequest(
        @NotNull
        val title: String
) {
    fun toGenre(): Genre = Genre(title = title)
}