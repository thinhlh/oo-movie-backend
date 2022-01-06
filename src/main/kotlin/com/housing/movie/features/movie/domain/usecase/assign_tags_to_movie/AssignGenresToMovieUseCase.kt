package com.housing.movie.features.movie.domain.usecase.assign_tags_to_movie

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import org.springframework.stereotype.Component

@Component
class AssignGenresToMovieUseCase(private val service: MovieService) : BaseUseCase {
    override fun invoke(data: Any?): Movie {
        val assignGenresToMovieRequest = data as AssignGenresToMovieRequest
        return service.assignGenresToMovie(assignGenresToMovieRequest.movieId, assignGenresToMovieRequest.genreIds)
    }
}