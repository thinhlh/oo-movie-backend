package com.housing.movie.features.movie.domain.usecase.update_movie

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import org.springframework.stereotype.Component

@Component
class UpdateMovieUseCase(
    private val movieService: MovieService
) : BaseUseCase {
    override fun invoke(data: Any?): Movie {
        return movieService.updateMovie(data as UpdateMovieRequest)
    }
}