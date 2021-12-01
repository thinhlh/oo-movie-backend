package com.housing.movie.config

import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Movie
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MovieConfiguration {
    @Bean
    fun initMovies(movieRepository: MovieRepository) = CommandLineRunner { args ->
        movieRepository.save(Movie(title = "Luca"))
        movieRepository.save(Movie(title = "Arcane"))
    }
}