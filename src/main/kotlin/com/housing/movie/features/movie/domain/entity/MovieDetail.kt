package com.housing.movie.features.movie.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import kotlin.jvm.Transient

@Entity
class MovieDetail(

    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @OneToOne(mappedBy = "movieDetail", fetch = FetchType.LAZY)
    val movie: Movie? = null,

    val adult: Boolean = true,

    val backdrop_path: String = "",

    val budget: Int = 0,

    val homepage: String = "",

    val originalLanguage: String = "",

    val originalTitle: String = "",

    @Column(columnDefinition = "TEXT")
    val overview: String = "",

    val posterPath: String = "",

    @Temporal(TemporalType.DATE)
    val releaseDate: Calendar? = null,

    val revenue: Long = 0,

    @NotBlank
    val title: String = "",

    var voteAverage: Double = 0.0,

    var voteCount: Long = 0,

    val likeCount: Long = 0,

    val viewCount: Long = 0,

    @Column(name = "is_tv_series")
    var isTVSeries: Boolean = false,

    var trailer1: String = "",

    var trailer2: String = "",

    @Transient
    val isMine: Boolean = true
)