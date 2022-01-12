package com.housing.movie.features.movie.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.service.MovieService
import org.springframework.stereotype.Component
import java.util.*

@Component
class IsMovieEligibleForUserUseCase(
    private val movieService: MovieService
) : BaseUseCase {
    override fun invoke(data: Any?): Boolean {
        return movieService.isMovieEligibleForUser(movieId = data as UUID)
    }

}