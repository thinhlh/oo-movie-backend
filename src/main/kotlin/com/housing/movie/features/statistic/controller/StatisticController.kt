package com.housing.movie.features.statistic.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.statistic.domain.usecase.TopMovieRatingUseCase
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanQueryParams
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanResponse
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class StatisticController(
    private val topMovieRatingUseCase: TopMovieRatingUseCase,
    private val topPurchasePlanUseCase: TopPurchasePlanUseCase
) : BaseController() {

    @GetMapping("/movies/rating")
    fun topMovieRating(@RequestParam size: Int): ResponseEntity<BaseResponse<List<Movie?>>> {
        return successResponse(topMovieRatingUseCase(size))
    }

    @GetMapping("/plans/order/top")
    fun topPLanRating( topPurchasePlanQueryParams: TopPurchasePlanQueryParams): ResponseEntity<BaseResponse<List<TopPurchasePlanResponse>>> {
        return successResponse(topPurchasePlanUseCase(topPurchasePlanQueryParams))
    }

}