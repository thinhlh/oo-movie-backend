package com.housing.movie.features.comment.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.features.comment.data.repository.CommentRepository
import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.entity.CommentStatus
import com.housing.movie.features.comment.domain.service.CommentService
import com.housing.movie.features.comment.domain.usecase.create_comment.CreateCommentRequest
import com.housing.movie.features.comment.domain.usecase.get_comments.CommentQueryParams
import com.housing.movie.features.comment.domain.usecase.update_comment.UpdateCommentRequest
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.user.data.repository.UserRepository
import com.housing.movie.utils.ServletHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository
) : CommentService {

    companion object {
        const val MOVIE_NOT_FOUND = "Movie not found."
        const val USER_NOT_FOUND = "User not found."
        const val COMMENT_NOT_FOUND = "Comment not found."
    }

    override fun getAllComments(commentQueryParams: CommentQueryParams): List<Comment> {
        val comments: List<Comment>
        val movieId = commentQueryParams.movie_id
        val status = commentQueryParams.status

        comments = if (movieId == null && status == null) {
            commentRepository.findAll().toList()
        } else if (movieId == null) {
            commentRepository.getByCommentStatus(status!!)
        } else if (status == null) {
            commentRepository.getByMovie_Id(movieId)
        } else {
            commentRepository.findByMovie_IdAndCommentStatus(movieId, status)
        }

        return comments
    }

    @Transactional
    override fun createComment(createCommentRequest: CreateCommentRequest): Comment {
        val movieId = createCommentRequest.movieId
        val username = ServletHelper.retrieveUsernameAndRoles().first

        val movie = movieRepository.findByIdOrNull(movieId) ?: throw NotFoundException(MOVIE_NOT_FOUND)
        val user = userRepository.findByUsername(username) ?: throw NotFoundException(USER_NOT_FOUND)

        var comment = Comment(
            content = createCommentRequest.content,
            user = user,
            movie = movie,
            commentStatus = CommentStatus.Pending
        )

        comment = commentRepository.save(comment)

        movie.comments.add(comment)
        user.comments.add(comment)

        return comment


    }

    @Transactional
    override fun updateCommentStatus(updateCommentRequest: UpdateCommentRequest): Comment {
        val comment = commentRepository.findByIdOrNull(updateCommentRequest.id) ?: throw NotFoundException(
            COMMENT_NOT_FOUND
        )

        comment.commentStatus = updateCommentRequest.status

        return comment
    }

}