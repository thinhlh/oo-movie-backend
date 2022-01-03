package com.housing.movie.features.plan.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.plan.domain.service.PlanService
import org.springframework.stereotype.Component
import java.util.*

@Component
class EnablePlanUseCase(
    private val planService: PlanService
) : BaseUseCase {
    override fun invoke(data: Any?): Plan {
        return planService.enablePlan(data as UUID)
    }
}