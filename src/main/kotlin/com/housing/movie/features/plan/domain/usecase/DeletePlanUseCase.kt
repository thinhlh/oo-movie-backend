package com.housing.movie.features.plan.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.plan.domain.service.PlanService
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeletePlanUseCase(
    private val planService: PlanService
) : BaseUseCase {
    override fun invoke(data: Any?): Boolean {
        return planService.deletePlan(data as UUID)
    }
}