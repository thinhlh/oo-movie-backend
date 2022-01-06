package com.housing.movie.features.company.domain

import com.housing.movie.features.movie.domain.entity.Movie
import java.util.*
import javax.persistence.*


@Entity
class Company(

    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    var name: String = "",
    var logoPath: String = "",
    var originCountry: String = "",

    @ManyToOne
    var parentCompany: Company? = null,

    @OneToMany(mappedBy = "parentCompany")
    var childrenCompany: List<Company> = emptyList(),

    var description: String = "",

    var homepage: String = "",

    var headquarters: String = "",

    @ManyToMany(mappedBy = "companies")
    var producedMovies: List<Movie> = emptyList(),

    var enabled: Boolean = true,

    )