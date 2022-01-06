package com.housing.movie.features.comment.domain.usecase.update_comment

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.service.CommentService
import org.springframework.stereotype.Component

@Component
class UpdateCommentUseCase(
    private val commentService: CommentService
) : BaseUseCase {
    override fun invoke(data: Any?): Comment {
        return commentService.updateCommentStatus(data as UpdateCommentRequest)
    }
}