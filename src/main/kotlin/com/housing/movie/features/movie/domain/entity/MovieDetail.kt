package com.housing.movie.features.movie.domain.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class MovieDetail(

    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @OneToOne(mappedBy = "movieDetail")
    val movie: Movie? = null,

    val adult: Boolean = true,

    val backdrop_path: String = "",

    val budget: Int = 0,

    val homepage: String = "",

    val originalLanguage: String = "",

    val originalTitle: String = "",

    val overview: String = "",

    val posterPath: String = "",

    @Temporal(TemporalType.DATE)
    val releaseDate: Calendar? = null,

    val revenue: Long = 0,

    @NotBlank
    val title: String = "",

    val voteAverage: Long = 0,

    val voteCount: Long = 0,

    val likeCount: Long = 0,

    val viewCount: Long = 0
)