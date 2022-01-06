package com.housing.movie.features.discount.domain.usecase.create_discount

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.service.DiscountService
import org.springframework.stereotype.Component

@Component
class CreateDiscountUseCase(
    private val discountService: DiscountService
) : BaseUseCase {
    override fun invoke(data: Any?): Discount {
        return discountService.createDiscount(data as CreateDiscountRequest)
    }

}