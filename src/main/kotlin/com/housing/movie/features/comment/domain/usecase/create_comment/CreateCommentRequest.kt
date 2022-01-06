package com.housing.movie.features.comment.domain.usecase.create_comment

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.comment.domain.entity.Comment
import java.util.*
import javax.validation.constraints.NotBlank

data class CreateCommentRequest(

    @NotBlank
    val content: String,

    @NotBlank
    @JsonProperty(value = "user_id")
    val userId: UUID,

    @NotBlank
    @JsonProperty(value = "movie_id")
    val movieId: UUID
) {

}
