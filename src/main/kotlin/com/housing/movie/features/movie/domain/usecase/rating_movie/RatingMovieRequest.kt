package com.housing.movie.features.movie.domain.usecase.rating_movie

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class RatingMovieRequest(
    @JsonProperty(value = "movie_id")
    @NotBlank
    val movieId: UUID,

    @Min(0, message = "Rating value must be equal or larger than 0")
    @Max(5, message = "Rating value must be equal or smaller than 5")
    val value: Int,
)