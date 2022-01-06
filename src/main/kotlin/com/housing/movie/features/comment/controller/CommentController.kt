package com.housing.movie.features.comment.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.usecase.create_comment.CreateCommentRequest
import com.housing.movie.features.comment.domain.usecase.create_comment.CreateCommentUseCase
import com.housing.movie.features.comment.domain.usecase.get_comments.CommentQueryParams
import com.housing.movie.features.comment.domain.usecase.get_comments.GetCommentsUseCase
import com.housing.movie.features.comment.domain.usecase.update_comment.UpdateCommentRequest
import com.housing.movie.features.comment.domain.usecase.update_comment.UpdateCommentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class CommentController(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val createCommentUseCase: CreateCommentUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase
) : BaseController() {

    @GetMapping("/comments")
    fun getComments(
        commentQueryParams: CommentQueryParams
    ): ResponseEntity<BaseResponse<List<Comment>>> {
        return successResponse(getCommentsUseCase(commentQueryParams))
    }

    @PostMapping("/comment")
    fun createComment(@RequestBody @Valid createCommentRequest: CreateCommentRequest): ResponseEntity<BaseResponse<Comment>> {
        return successResponse(createCommentUseCase(createCommentRequest))
    }

    @PutMapping("/comment")
    fun updateComment(@RequestBody @Valid updateCommentRequest: UpdateCommentRequest): ResponseEntity<BaseResponse<Comment>> {
        return successResponse(updateCommentUseCase(updateCommentRequest))
    }
}