package com.housing.movie.features.comment.domain.entity

enum class CommentStatus(val value: String) {
    Pending("PENDING"),
    Accept("ACCEPT"),
    Reject("REJECT")
}