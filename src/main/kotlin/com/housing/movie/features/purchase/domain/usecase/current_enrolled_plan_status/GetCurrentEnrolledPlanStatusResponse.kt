package com.housing.movie.features.purchase.domain.usecase.current_enrolled_plan_status

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.plan.domain.entity.Plan
import java.util.*

data class GetCurrentEnrolledPlanStatusResponse(
    val plan: Plan?,

    @JsonProperty(value = "expired_in")
    val expiredIn: Calendar

)