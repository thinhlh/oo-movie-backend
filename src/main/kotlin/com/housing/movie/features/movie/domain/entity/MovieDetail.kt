package com.housing.movie.features.movie.domain.entity

import java.util.*
import javax.persistence.*

@Entity
class MovieDetail(
        @Id
        @GeneratedValue
        val id: UUID = UUID.randomUUID(),

        @OneToOne(mappedBy = "movieDetail")
        val movie: Movie? = null,

        val adult: Boolean = true,

        val backdrop_path: String? = null,

        val budget: Int? = null,

        val homepage: String? = null,

        val originalLanguage: String? = null,

        val originalTitle: String? = null,

        val overview: String? = null,

        val posterPath: String? = null,

        @Temporal(TemporalType.DATE)
        val releaseDate: Calendar? = null,

        val revenue: Long? = null,

        val title: String? = null,

        val voteAverage: Long? = null,

        val voteCount: Long? = null,

        val likeCount: Long? = null,

        val viewCount: Long? = null
)