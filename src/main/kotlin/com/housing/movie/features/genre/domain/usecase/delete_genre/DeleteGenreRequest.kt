package com.housing.movie.features.genre.domain.usecase.delete_genre

import java.util.*
import javax.validation.constraints.NotEmpty


data class DeleteGenreRequest(
    @NotEmpty(message = "ID must not be empty")
    val id: UUID
)