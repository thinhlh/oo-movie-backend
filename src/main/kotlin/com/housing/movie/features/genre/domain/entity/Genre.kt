package com.housing.movie.features.genre.domain.entity

import com.housing.movie.features.movie.domain.entity.Movie
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Genre(
        @Id
        @GeneratedValue
        val id: UUID = UUID.randomUUID(),

        val title: String? = null,

        val enabled: Boolean = true,

        @ManyToMany(mappedBy = "genres")
        val movies: List<Movie>? = null

)

