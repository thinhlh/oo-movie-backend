package com.housing.movie.features.movie.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetMoviesByGenreUseCase(private val movieService: MovieService) : BaseUseCase {
    override fun invoke(data: Any?): List<Movie> {
        return movieService.getMoviesByGenre(data as UUID)
    }
}