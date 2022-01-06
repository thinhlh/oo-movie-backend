package com.housing.movie.features.comment.domain.service

import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.usecase.create_comment.CreateCommentRequest
import com.housing.movie.features.comment.domain.usecase.get_comments.CommentQueryParams
import com.housing.movie.features.comment.domain.usecase.update_comment.UpdateCommentRequest

interface CommentService {
    fun getAllComments(commentQueryParams: CommentQueryParams): List<Comment>

    fun createComment(createCommentRequest: CreateCommentRequest): Comment

    fun updateCommentStatus(updateCommentRequest: UpdateCommentRequest): Comment
}