package com.housing.movie.features.movie.domain.usecase.assign_tags_to_movie

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class AssignGenresToMovieRequest(
    @NotBlank
    val movieId: UUID,

    @NotEmpty
    val genreIds: List<UUID>
)