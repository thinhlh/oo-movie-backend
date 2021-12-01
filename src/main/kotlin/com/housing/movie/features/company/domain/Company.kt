package com.housing.movie.features.company.domain

import com.housing.movie.features.movie.domain.entity.Movie
import java.util.*
import javax.persistence.*


@Entity
class Company(
        @Id
        @GeneratedValue
        var id: UUID = UUID.randomUUID(),

        var name: String? = null,
        var logoPath: String? = null,
        var originCountry: String? = null,

        @ManyToOne
        var parentCompany: Company? = null,

        @OneToMany(mappedBy = "parentCompany")
        var childrenCompany: List<Company>? = null,

        var description: String? = null,

        var homepage: String? = null,

        var headquarters: String? = null,

        var enabled: Boolean = true,

        @ManyToMany(mappedBy = "companies")
        var producedMovies: List<Movie>? = null
)