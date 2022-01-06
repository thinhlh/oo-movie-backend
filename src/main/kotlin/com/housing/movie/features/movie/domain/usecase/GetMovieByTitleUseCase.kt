package com.housing.movie.features.movie.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@RequiredArgsConstructor
@Component
class GetMovieByTitleUseCase(
        private val movieService: MovieService
) : BaseUseCase {
    override fun invoke(data: Any?): Movie {
        return movieService.getMovieByTitle(data as String)
    }
}