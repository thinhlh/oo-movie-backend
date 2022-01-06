package com.housing.movie.features.movie.domain.usecase.update_movie

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.housing.movie.exceptions.MissingPropertyException
import com.housing.movie.features.movie.domain.entity.MovieDetail
import com.housing.movie.utils.DateTimeHelper
import org.springframework.web.bind.MissingServletRequestParameterException
import java.lang.NullPointerException
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateMovieRequest(

    @NotNull
    @NotBlank
    val id: UUID,

    val adult: Boolean? = null,

    val backdrop_path: String? = null,

    val budget: Int? = null,

    val homepage: String? = null,

    @JsonProperty(value = "original_language")
    val originalLanguage: String? = null,

    @JsonProperty(value = "original_title")
    val originalTitle: String? = null,

    val overview: String? = null,

    @JsonProperty(value = "poster_path")
    val posterPath: String? = null,

    @JsonProperty(value = "release_date")
    val releaseDate: String? = null,

    val revenue: Long? = null,

    @NotBlank
    val title: String? = null,

    @JsonProperty(value = "vote_average")
    val voteAverage: Long? = null,

    @JsonProperty(value = "vote_count")
    val voteCount: Long? = null,

    @JsonProperty(value = "like_count")
    val likeCount: Long? = null,

    @JsonProperty(value = "view_count")
    val viewCount: Long? = null,

    @JsonProperty(value = "genre_ids")
    val genreIds: List<UUID>? = null,

    @JsonProperty(value = "is_tv_series")
    val isTVSeries: Boolean? = null,

    @JsonProperty(value = "movie_id_fake")
    val movieIdFake: String? = null
) {
    fun toMovieDetail(currentMovieDetail: MovieDetail?): MovieDetail {

        try {
            if (currentMovieDetail == null) {
                return MovieDetail(
                    adult = adult!!,
                    backdrop_path = backdrop_path!!,
                    budget = budget!!,
                    homepage = homepage!!,
                    originalLanguage = originalLanguage!!,
                    originalTitle = originalTitle!!,
                    overview = overview!!,
                    posterPath = posterPath!!,
                    releaseDate = DateTimeHelper.stringToCalendar(releaseDate),
                    revenue = revenue!!,
                    title = title!!,
                    voteAverage = voteAverage!!,
                    voteCount = voteCount!!,
                    likeCount = likeCount!!,
                    viewCount = viewCount!!,
                    isTVSeries = isTVSeries!!
                )
            }
        } catch (e: NullPointerException) {
            throw MissingPropertyException(message = e.message)
        }


        return MovieDetail(
            id = currentMovieDetail.id,
            adult = adult ?: currentMovieDetail.adult,
            backdrop_path = backdrop_path ?: currentMovieDetail.backdrop_path,
            budget = budget ?: currentMovieDetail.budget,
            homepage = homepage ?: currentMovieDetail.homepage,
            originalLanguage = originalLanguage ?: currentMovieDetail.originalLanguage,
            originalTitle = originalTitle ?: currentMovieDetail.originalTitle,
            overview = overview ?: currentMovieDetail.overview,
            posterPath = posterPath ?: currentMovieDetail.posterPath,
            releaseDate = DateTimeHelper.stringToCalendar(releaseDate) ?: currentMovieDetail.releaseDate,
            revenue = revenue ?: currentMovieDetail.revenue,
            title = title ?: currentMovieDetail.title,
            voteAverage = voteAverage ?: currentMovieDetail.voteAverage,
            voteCount = voteCount ?: currentMovieDetail.voteCount,
            likeCount = likeCount ?: currentMovieDetail.likeCount,
            viewCount = viewCount ?: currentMovieDetail.viewCount,
            isTVSeries = isTVSeries ?: currentMovieDetail.isTVSeries,
        )
    }
}