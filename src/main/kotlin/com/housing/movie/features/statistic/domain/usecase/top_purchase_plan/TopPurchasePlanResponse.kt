package com.housing.movie.features.statistic.domain.usecase.top_purchase_plan

import java.util.*

data class TopPurchasePlanResponse(
    val planId: UUID,
    val title: String,
    val total: Double,
)