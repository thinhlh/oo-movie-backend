package com.housing.movie.features.comment.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.user.domain.entity.ApplicationUser
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import kotlin.jvm.Transient

@Entity
class Comment(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(columnDefinition = "TEXT")
    val content: String = "",

    @NotEmpty
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: ApplicationUser? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: Movie? = null,

    @Temporal(value = TemporalType.TIMESTAMP)
    val time: Calendar = Calendar.getInstance(),

    @Enumerated(value = EnumType.STRING)
    @JsonProperty(value = "status")
    var commentStatus: CommentStatus = CommentStatus.Pending,

    @Transient
    @JvmField
    val movieId: UUID? = null

) {
    // Movie id property for json response
    @JsonProperty(value = "movie_id")
    fun getMovieId() = movie?.id

    data class UserInfo(
        val id: UUID?,
        val name: String?
    )

    @JsonProperty(value = "user")
    fun getUserInfo(): UserInfo {
        return UserInfo(user?.id, user?.fullname)
    }
}