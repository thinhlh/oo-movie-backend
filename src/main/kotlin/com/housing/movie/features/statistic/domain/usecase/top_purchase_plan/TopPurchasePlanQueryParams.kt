package com.housing.movie.features.statistic.domain.usecase.top_purchase_plan

import com.fasterxml.jackson.annotation.JsonProperty

data class TopPurchasePlanQueryParams(

    val start_date: String? = null,

    val end_date: String? = null
)