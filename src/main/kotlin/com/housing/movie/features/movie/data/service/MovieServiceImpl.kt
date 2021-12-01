package com.housing.movie.features.movie.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
        private val movieRepository: MovieRepository
) : MovieService {

    override fun getAllMovies(): List<Movie> {
        return movieRepository.getAllByEnabledIsTrue()
    }

    override fun getMovieByTitle(title: String): Movie {
        return movieRepository.getMovieByTitle(title) ?: throw NotFoundException("Movie Not Found")
    }
}