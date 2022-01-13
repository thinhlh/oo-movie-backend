package com.housing.movie.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.plan.data.repository.PlanRepository
import com.housing.movie.features.plan.domain.entity.Plan
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream


@Configuration
class MovieConfiguration {
    @Bean
    fun initMovies(movieRepository: MovieRepository, planRepository: PlanRepository) = CommandLineRunner { args ->
        planRepository.save(Plan(title = "1 thang", price = 3.0, expired = 30, description = "1 thang xem tat ca"))
        planRepository.save(Plan(title = "2 thang", price = 6.0, expired = 60, description = "2 thang xem tat ca"))
        planRepository.save(Plan(title = "3 thang", price = 9.0, expired = 90, description = "3 thang xem tat ca"))
    }
}