package com.housing.movie.features.comment.domain.usecase.update_comment

import com.housing.movie.features.comment.domain.entity.CommentStatus
import java.util.*
import javax.validation.constraints.NotBlank

data class UpdateCommentRequest(
    @NotBlank
    val id: UUID,

    @NotBlank
    val status: CommentStatus
)