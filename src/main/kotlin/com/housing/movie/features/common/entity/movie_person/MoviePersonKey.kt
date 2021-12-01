package com.housing.movie.features.common.entity.movie_person

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Embeddable
class MoviePersonKey(

        @Column(name = "movie_id")
        val movieId: UUID? = null,

        @Column(name = "person_id")
        val personId: UUID? = null

) : Serializable