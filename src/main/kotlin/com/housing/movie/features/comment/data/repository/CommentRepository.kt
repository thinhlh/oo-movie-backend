package com.housing.movie.features.comment.data.repository

import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.comment.domain.entity.CommentStatus
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository : CrudRepository<Comment, UUID> {
    fun getByMovie_Id(movieId: UUID): List<Comment>

    fun getByCommentStatus(commentStatus: CommentStatus): List<Comment>

    fun findByMovie_IdAndCommentStatus(id: UUID, commentStatus: CommentStatus): List<Comment>

}