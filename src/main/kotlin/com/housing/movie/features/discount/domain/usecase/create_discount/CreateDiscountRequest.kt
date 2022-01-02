package com.housing.movie.features.discount.domain.usecase.create_discount

import com.fasterxml.jackson.annotation.JsonProperty
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.utils.DateTimeHelper
import java.util.*
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.validation.constraints.NotEmpty

data class CreateDiscountRequest(
    @NotEmpty
    val name: String = "",

    val description: String = "",

    @JsonProperty(value = "remaining_amount")
    val remainingAmount: Int = 0,

    @JsonProperty(value = "time_begin")
    val timeBegin: String = "",

    @JsonProperty(value = "time_end")
    val timeEnd: String = "",

    val value: Double = 0.0,

    @NotEmpty
    val code: String = "",
) {
    fun toDiscount(): Discount {
        return Discount(
            name = name,
            description = description,
            remainingAmount = remainingAmount,
            timeBegin = DateTimeHelper.stringToCalendar(timeBegin),
            timeEnd = DateTimeHelper.stringToCalendar(timeEnd),
            value = value,
            code = code
        )
    }
}