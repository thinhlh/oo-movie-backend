package com.housing.movie.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.housing.movie.features.movie.data.repository.MovieRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream


@Configuration
class MovieConfiguration {
    @Bean
    fun initMovies(movieRepository: MovieRepository) = CommandLineRunner { args ->

    }
}