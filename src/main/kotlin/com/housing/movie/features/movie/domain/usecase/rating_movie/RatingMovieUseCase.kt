package com.housing.movie.features.movie.domain.usecase.rating_movie

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.service.MovieService
import org.springframework.stereotype.Component

@Component
class RatingMovieUseCase(
    private val movieService: MovieService
) : BaseUseCase {
    override fun invoke(data: Any?): Double {
        return movieService.ratingMovie(data as RatingMovieRequest)
    }
}