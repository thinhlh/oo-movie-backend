package com.housing.movie.features.purchase.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.service.MovieService
import com.housing.movie.features.purchase.domain.service.PurchasedService
import org.springframework.stereotype.Component

@Component
class GetPurchasedMoviesUseCase(
    private val purchasedService: PurchasedService
) : BaseUseCase {
    override fun invoke(data: Any?): List<Movie> {
        return purchasedService.purchasedMovies()
    }
}