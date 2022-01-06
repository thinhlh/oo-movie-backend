package com.housing.movie.features.discount.domain.usecase.update_discount

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.service.DiscountService
import com.housing.movie.features.discount.domain.usecase.update_discount.UpdateDiscountRequest
import org.springframework.stereotype.Component

@Component
class UpdateDiscountUseCase(
    private val discountService: DiscountService
) : BaseUseCase {
    override fun invoke(data: Any?): Discount {
        return discountService.updateDiscount(data as UpdateDiscountRequest)
    }
}