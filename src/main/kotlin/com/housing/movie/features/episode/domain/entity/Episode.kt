package com.housing.movie.features.episode.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.movie.domain.entity.Movie
import java.util.*
import javax.persistence.*

@Entity
class Episode(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    var movie: Movie? = null,

    var name: String = "",
    var content: String = "",
    var ordinal: Int = 0,

    @JsonProperty(value = "view_count")
    var viewCount: Int = 0,
    var enabled: Boolean = true,
)