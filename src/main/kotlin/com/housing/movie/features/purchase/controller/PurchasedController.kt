package com.housing.movie.features.purchase.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.purchase.domain.usecase.GetPurchasedMoviesUseCase
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.purchase.domain.usecase.current_enrolled_plan_status.GetCurrentEnrolledPlanStatusResponse
import com.housing.movie.features.purchase.domain.usecase.current_enrolled_plan_status.GetCurrentEnrolledPlanStatusUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PurchasedController(
    private val getPurchasedMoviesUseCase: GetPurchasedMoviesUseCase,
    private val getCurrentEnrolledPlanStatusUseCase: GetCurrentEnrolledPlanStatusUseCase,
) : BaseController() {

    @GetMapping("/purchased/movies")
    fun getPurchasedMovies(): ResponseEntity<BaseResponse<List<Movie>>> {
        return successResponse(getPurchasedMoviesUseCase())
    }

    @GetMapping("/purchased/plan")
    fun getPurchasedPlan(): ResponseEntity<BaseResponse<GetCurrentEnrolledPlanStatusResponse>> {
        return successResponse(getCurrentEnrolledPlanStatusUseCase())
    }

}