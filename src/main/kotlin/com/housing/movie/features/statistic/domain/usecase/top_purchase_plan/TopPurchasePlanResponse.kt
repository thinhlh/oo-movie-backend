package com.housing.movie.features.statistic.domain.usecase.top_purchase_plan

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class TopPurchasePlanResponse(
    @JsonProperty(value = "plan_id")
    val planId: UUID,
    val title: String,
    val total: Double,
)