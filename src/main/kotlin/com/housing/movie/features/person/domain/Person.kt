package com.housing.movie.features.person.domain

//import com.housing.movie.features.common.entity.movie_person.MoviePerson
import com.housing.movie.features.movie.domain.entity.Movie
import java.time.LocalDate
import java.util.*
import javax.annotation.processing.Generated
import javax.persistence.*

@Entity
class Person(
        @Id
        @GeneratedValue
        val id: UUID = UUID.randomUUID(),

        @Temporal(TemporalType.DATE)
        val birthday: Calendar? = null,

        val knownForDepartment: String? = null,

        @Temporal(TemporalType.DATE)
        val deathDay: Calendar? = null,

        // 0 for male, 1 for female
        val gender: Boolean? = null,

        val biography: String? = null,

        val placeOfBirth: String? = null,

        val profilePath: String? = null,

        val adult: Boolean? = null,

        val homepage: String? = null,

        val enabled: Boolean = true,

//        @OneToMany(mappedBy = "person")
//        var moviePersons: List<MoviePerson>? = null

)