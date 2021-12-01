package com.housing.movie.features.movie.domain.entity

import com.housing.movie.features.common.entity.movie_person.MoviePerson
import com.housing.movie.features.company.domain.Company
import com.housing.movie.features.genre.domain.entity.Genre
import java.util.*
import javax.persistence.*


@Entity
class Movie(
        @Id
        @GeneratedValue
        val id: UUID = UUID.randomUUID(),

        val title: String? = null,

        val enabled: Boolean = true,

        @ManyToMany
        @JoinTable(name = "movie_genre", joinColumns = [JoinColumn(name = "movie_id")], inverseJoinColumns = [JoinColumn(name = "genre_id")])
        val genres: List<Genre>? = null,

        @ManyToMany
        @JoinTable(name = "movie_company", joinColumns = [JoinColumn(name = "movie_id")], inverseJoinColumns = [JoinColumn(name = "company_id")])
        var companies: List<Company>? = null,

        @OneToMany(mappedBy = "movie")
        var moviePersons: List<MoviePerson>? = null,
//
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "movie_detail_id", referencedColumnName = "id")
        var movieDetail: MovieDetail? = null,


        )