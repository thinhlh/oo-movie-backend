package com.housing.movie.features.movie.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.housing.movie.features.common.entity.movie_person.MoviePerson
import com.housing.movie.features.common.serializers.MovieSerializer
import com.housing.movie.features.company.domain.Company
import com.housing.movie.features.episode.domain.entity.Episode
import com.housing.movie.features.genre.domain.entity.Genre
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
@JsonSerialize(using = MovieSerializer::class)
class Movie(
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID(),

    @NotBlank
    var title: String = "",

    @ManyToMany
    @JoinTable(
        name = "movie_genre",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    var genres: List<Genre> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "movie_company",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "company_id")]
    )
    @JsonIgnore
    var companies: List<Company> = emptyList(),

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    @JsonProperty(value = "movie_people")
    var moviePersons: List<MoviePerson>? = emptyList(),

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_detail_id", referencedColumnName = "id")
    @JsonProperty(value = "movie_detail")
    var movieDetail: MovieDetail? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    val episodes: MutableList<Episode> = mutableListOf(),

    @JsonProperty(value = "movie_id_fake")
    var movieIdFake: String = "",

    var enabled: Boolean = true

)