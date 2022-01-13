package com.housing.movie.features.purchase.domain.service

import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.purchase.domain.usecase.current_enrolled_plan_status.GetCurrentEnrolledPlanStatusResponse

interface PurchasedService {
    fun purchasedMovies(): List<Movie>

    fun getCurrentEnrolledPlanStatus(): GetCurrentEnrolledPlanStatusResponse
}