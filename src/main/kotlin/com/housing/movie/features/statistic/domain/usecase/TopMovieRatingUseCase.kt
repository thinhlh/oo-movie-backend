package com.housing.movie.features.statistic.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.service.MovieService
import com.housing.movie.features.movie.domain.usecase.rating_movie.RatingMovieRequest
import org.springframework.stereotype.Component

@Component
class TopMovieRatingUseCase(
    private val movieService: MovieService
) : BaseUseCase {
    override fun invoke(data: Any?): Double {
        return 0.0
//        return movieService.ratingMovie(data as RatingMovieRequest)
    }
}