package com.housing.movie.features.purchase.domain.usecase.current_enrolled_plan_status

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.purchase.domain.service.PurchasedService
import org.springframework.stereotype.Component

@Component
class GetCurrentEnrolledPlanStatusUseCase(
    private val purchasedService: PurchasedService
) : BaseUseCase {
    override fun invoke(data: Any?): GetCurrentEnrolledPlanStatusResponse {
        return purchasedService.getCurrentEnrolledPlanStatus()
    }
}