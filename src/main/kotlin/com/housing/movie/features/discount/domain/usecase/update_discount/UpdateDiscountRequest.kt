package com.housing.movie.features.discount.domain.usecase.update_discount

import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.utils.DateTimeHelper
import java.util.*
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.validation.constraints.NotEmpty

data class UpdateDiscountRequest(
    @NotEmpty
    val id: UUID,

    var name: String? = null,

    var description: String? = null,

    var remainingAmount: Int? = null,

    var timeBegin: String? = null,

    var timeEnd: String? = null,

    var value: Double? = null,

    var code: String? = null,
) {
    fun updateDiscount(oldDiscount: Discount): Discount {
        oldDiscount.name = name ?: oldDiscount.name
        oldDiscount.description = description ?: oldDiscount.description
        oldDiscount.remainingAmount = remainingAmount ?: oldDiscount.remainingAmount
        oldDiscount.timeBegin = DateTimeHelper.stringToCalendar(timeBegin) ?: oldDiscount.timeBegin
        oldDiscount.timeEnd = DateTimeHelper.stringToCalendar(timeEnd) ?: oldDiscount.timeEnd
        oldDiscount.value = value ?: oldDiscount.value
        oldDiscount.code = code ?: oldDiscount.code

        return oldDiscount
    }
}