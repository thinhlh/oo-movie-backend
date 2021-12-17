package com.housing.movie.features.movie.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.housing.movie.features.common.entity.movie_person.MoviePerson
import com.housing.movie.features.company.domain.Company
import com.housing.movie.features.genre.domain.entity.Genre
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
class Movie(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @NotBlank
    var title: String = "",

    @ManyToMany
    @JoinTable(
        name = "movie_genre",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )

    var genres: List<Genre>? = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "movie_company",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "company_id")]
    )
    @JsonIgnore
    var companies: List<Company>? = emptyList(),

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    var moviePersons: List<MoviePerson>? = emptyList(),

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "movie_detail_id", referencedColumnName = "id")
    var movieDetail: MovieDetail? = null,

    var enabled: Boolean = true

)