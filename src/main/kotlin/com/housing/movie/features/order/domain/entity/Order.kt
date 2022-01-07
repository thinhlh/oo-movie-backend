package com.housing.movie.features.order.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.user.domain.entity.ApplicationUser
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "app_order")
class Order(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var user: ApplicationUser? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var discount: Discount? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var plan: Plan? = null,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "order_movie",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    var movies: MutableList<Movie> = mutableListOf(),

    val isPlan: Boolean = true,

    @Temporal(value = TemporalType.TIMESTAMP)
    val orderTime: Calendar = Calendar.getInstance(),

    var enabled: Boolean = true

)