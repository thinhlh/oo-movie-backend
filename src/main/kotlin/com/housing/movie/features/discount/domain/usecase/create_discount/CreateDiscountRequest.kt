package com.housing.movie.features.discount.domain.usecase.create_discount

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

    val remainingAmount: Int = 0,

    val timeBegin: String = "",

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