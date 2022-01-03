package com.housing.movie.features.plan.domain.service

import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.plan.domain.usecase.update_plan.UpdatePlanRequest
import java.util.*

interface PlanService {
    fun getPlans(): List<Plan>

    fun deletePlan(id: UUID): Boolean

    fun enablePlan(id: UUID): Plan

    fun updatePlan(updatePlanRequest: UpdatePlanRequest): Plan
}