package com.housing.movie.features.movie.domain.usecase.create_movie

import com.housing.movie.features.movie.domain.entity.MovieDetail
import com.housing.movie.utils.DateTimeHelper
import java.util.*
import javax.validation.constraints.NotBlank

data class CreateMovieRequest(

    val adult: Boolean = true,

    val backdrop_path: String = "",

    val budget: Int = 0,

    val homepage: String = "",

    val originalLanguage: String = "",

    val originalTitle: String = "",

    val overview: String = "",

    val posterPath: String = "",

    val releaseDate: String? = null,

    val revenue: Long = 0,

    @NotBlank
    val title: String,

    val voteAverage: Long = 0,

    val voteCount: Long = 0,

    val likeCount: Long = 0,

    val viewCount: Long = 0,

    val genreIds: List<UUID> = mutableListOf()

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
            viewCount = viewCount
        )
    }
}