package com.housing.movie.features.movie.domain.usecase.create_movie

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.movie.domain.entity.MovieDetail
import com.housing.movie.utils.DateTimeHelper
import java.util.*
import javax.validation.constraints.NotBlank

data class CreateMovieRequest(

    val id: UUID? = null,

    val adult: Boolean = true,

    val backdrop_path: String = "",

    val budget: Int = 0,

    val homepage: String = "",

    @JsonProperty(value = "original_language")
    val originalLanguage: String = "",

    @JsonProperty(value = "original_title")
    val originalTitle: String = "",

    val overview: String = "",

    @JsonProperty(value = "poster_path")
    val posterPath: String = "",

    @JsonProperty(value = "release_date")
    val releaseDate: String? = null,

    val revenue: Long = 0,

    @NotBlank
    val title: String,

    @JsonProperty(value = "vote_average")
    val voteAverage: Double = 0.0,

    @JsonProperty(value = "vote_count")
    val voteCount: Long = 0,

    @JsonProperty(value = "like_count")
    val likeCount: Long = 0,

    @JsonProperty(value = "view_count")
    val viewCount: Long = 0,

    @JsonProperty(value = "genre_ids")
    val genreIds: List<UUID> = mutableListOf(),

    @JsonProperty(value = "movie_id_fake")
    val movieIdFake: String = "",

    @JsonProperty(value = "is_tv_series")
    val isTVSeries: Boolean = false

) {
    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            adult = adult,
            backdrop_path = backdrop_path,
            budget = budget,
            homepage = homepage,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            releaseDate = DateTimeHelper.stringToCalendar(releaseDate),
            revenue = revenue,
            title = title,
            voteAverage = voteAverage,
            voteCount = voteCount,
            likeCount = likeCount,
            viewCount = viewCount,
            isTVSeries = isTVSeries
        )
    }
}