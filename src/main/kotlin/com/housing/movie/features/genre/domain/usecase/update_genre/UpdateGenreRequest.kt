package com.housing.movie.features.genre.domain.usecase.update_genre


import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.genre.domain.entity.Genre
import java.util.*

data class UpdateGenreRequest(
    val id: UUID,
    val title: String
) {
    fun toGenre(): Genre {
        return Genre(id, title)
    }
}