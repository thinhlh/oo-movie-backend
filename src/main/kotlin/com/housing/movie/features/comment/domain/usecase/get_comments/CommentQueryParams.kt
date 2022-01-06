package com.housing.movie.features.comment.domain.usecase.get_comments

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.comment.domain.entity.CommentStatus
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

data class CommentQueryParams(

    val movie_id: UUID?,
    val status: CommentStatus?
)
