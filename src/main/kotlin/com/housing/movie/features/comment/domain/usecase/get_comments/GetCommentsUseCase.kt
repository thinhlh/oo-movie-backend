package com.housing.movie.features.comment.domain.usecase.get_comments

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.service.CommentService
import org.springframework.stereotype.Component

@Component
class GetCommentsUseCase(
    private val commentService: CommentService
) : BaseUseCase {
    override fun invoke(data: Any?): List<Comment> {
        return commentService.getAllComments(data as CommentQueryParams)
    }

}