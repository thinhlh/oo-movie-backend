package com.housing.movie.features.order.domain.service

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class OrderQueryParams(
    val user_id: UUID? = null,

    val from_time: Long? = null,

    val to_time: Long? = null
)