package com.housing.movie.features.plan.domain.service

import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.plan.domain.usecase.update_plan.UpdatePlanRequest
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanQueryParams
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanResponse
import java.util.*

interface PlanService {
    fun getPlans(): List<Plan>

    fun deletePlan(id: UUID): Boolean

    fun enablePlan(id: UUID): Plan

    fun updatePlan(updatePlanRequest: UpdatePlanRequest): Plan

    fun topPurchasePlan(topPurchasePlanQueryParams: TopPurchasePlanQueryParams): List<TopPurchasePlanResponse>
}