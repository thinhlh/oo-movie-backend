package com.housing.movie.features.movie.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Episode(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id")
    var movie: Movie? = null,

    var name: String = "",
    var content: String = "",
    var ordinal: Int = 0,
    var viewCount: Int = 0,
    var enabled: Boolean = true,
)