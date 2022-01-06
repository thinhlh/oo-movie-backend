package com.housing.movie.features.genre.domain.usecase.update_genre


import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.genre.domain.entity.Genre
import java.util.*
import javax.validation.constraints.NotEmpty

data class UpdateGenreRequest(

    @NotEmpty
    val id: UUID,
    val name: String,
    @JsonProperty(value = "genre_id_fake")
    val genreIdFake: String
) {
    fun toGenre(): Genre {
        return Genre(id, name)
    }
}