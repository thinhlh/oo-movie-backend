package com.housing.movie.features.common.entity.movie_person

import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.person.domain.Person
import javax.persistence.*

@Entity
class MoviePerson(
        @EmbeddedId
        val id: MoviePersonKey? = null,

        @ManyToOne
        @MapsId("movieId")
        @JoinColumn(name = "movie_id")
        val movie: Movie? = null,

        @ManyToOne
        @MapsId("personId")
        @JoinColumn(name = "person_id")
        val person: Person? = null,

        val character: String? = null,
        val department: String? = null,
        val job: String? = null,
        @Enumerated(EnumType.STRING)
        val personType: PersonType? = null,
)