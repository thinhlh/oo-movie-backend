package com.housing.movie.features.genre.domain.usecase.create_genre

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.genre.domain.entity.Genre
import javax.validation.constraints.NotNull


data class CreateGenreRequest(
    @NotNull
    val name: String,

    @JsonProperty(value = "genre_id_fake")
    val genreIdFake: String
) {
    fun toGenre(): Genre = Genre(name = name, genreIdFake = genreIdFake)
}