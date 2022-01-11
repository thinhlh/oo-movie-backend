package com.housing.movie.features.statistic.domain.usecase.top_purchase_plan

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.plan.domain.service.PlanService
import org.springframework.stereotype.Component

@Component
class TopPurchasePlanUseCase(
    private val planService: PlanService
) : BaseUseCase {
    override fun invoke(data: Any?): List<TopPurchasePlanResponse> {
        return planService.topPurchasePlan(data as TopPurchasePlanQueryParams)
    }
}