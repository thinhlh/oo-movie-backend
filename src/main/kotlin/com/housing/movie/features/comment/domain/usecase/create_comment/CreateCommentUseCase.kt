package com.housing.movie.features.comment.domain.usecase.create_comment

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.service.CommentService
import org.springframework.stereotype.Component

@Component
class CreateCommentUseCase(
    private val commentService: CommentService
) : BaseUseCase {
    override fun invoke(data: Any?): Comment {
        return commentService.createComment(data as CreateCommentRequest)
    }
}