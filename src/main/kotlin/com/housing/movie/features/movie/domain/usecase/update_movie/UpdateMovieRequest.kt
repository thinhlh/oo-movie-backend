package com.housing.movie.features.movie.domain.usecase.update_movie

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

    val originalLanguage: String? = null,

    val originalTitle: String? = null,

    val overview: String? = null,

    val posterPath: String? = null,

    val releaseDate: String? = null,

    val revenue: Long? = null,

    @NotBlank
    val title: String? = null,

    val voteAverage: Long? = null,

    val voteCount: Long? = null,

    val likeCount: Long? = null,

    val viewCount: Long? = null,

    val genreIds: List<UUID>? = null
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
                    viewCount = viewCount!!
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
            viewCount = viewCount ?: currentMovieDetail.viewCount
        )
    }
}