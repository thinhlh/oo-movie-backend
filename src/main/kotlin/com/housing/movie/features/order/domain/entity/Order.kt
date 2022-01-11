package com.housing.movie.features.order.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.common.entity.extended_response.MovieInfo
import com.housing.movie.features.common.entity.extended_response.PlanInfo
import com.housing.movie.features.common.entity.extended_response.UserInfo
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

    @JsonProperty(value = "order_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    val orderTime: Calendar = Calendar.getInstance(),

    val total: Double = 0.0,

    var enabled: Boolean = true

) {

    @JsonProperty(value = "movies")
    fun getMoviesInfo(): List<MovieInfo> {
        return movies.map { movie -> MovieInfo(movie.id, movie.title, movie.price) }
    }


    @JsonProperty(value = "plan")
    fun getPlanInfo(): PlanInfo? {
        plan ?: return null
        return PlanInfo(plan!!.id, plan!!.title, plan!!.price)
    }

    @JsonProperty(value = "user")
    fun getUserInfo(): UserInfo? {
        return user?.toUserInfo()
    }

    @JsonProperty(value = "is_plan")
    fun getIsPlan(): Boolean {
        return movies.isEmpty()
    }
}