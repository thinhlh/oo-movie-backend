package com.housing.movie.features.plan.domain.usecase.update_plan

import com.housing.movie.features.plan.domain.entity.Plan
import java.util.*
import javax.validation.constraints.NotEmpty

data class UpdatePlanRequest(
    @NotEmpty
    val id: UUID,

    val price: Double? = null,

    val title: String? = null,

    val description: String? = null
) {
    fun updatePlan(oldPlan: Plan): Plan {
        oldPlan.price = price ?: oldPlan.price
        oldPlan.title = title ?: oldPlan.title
        oldPlan.description = description ?: oldPlan.description

        return oldPlan
    }
}