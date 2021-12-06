package com.housing.movie.features.genre.domain.usecase.update_genre


import com.housing.movie.features.genre.domain.entity.Genre
import java.util.*

data class UpdateGenreRequest(
    val id: UUID,
    val name: String
) {
    fun toGenre(): Genre {
        return Genre(id, name)
    }
}