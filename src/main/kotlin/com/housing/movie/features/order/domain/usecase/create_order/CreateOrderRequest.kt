package com.housing.movie.features.order.domain.usecase.create_order

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.exceptions.MissingPropertyException
import com.housing.movie.features.order.domain.entity.Order
import java.util.*

data class CreateOrderRequest(

    @JsonProperty(value = "movie_ids")
    val movieIds: List<UUID>?,

    @JsonProperty(value = "plan_id")
    val planId: UUID?,

    @JsonProperty(value = "discount_code")
    val discountCode: String?
)