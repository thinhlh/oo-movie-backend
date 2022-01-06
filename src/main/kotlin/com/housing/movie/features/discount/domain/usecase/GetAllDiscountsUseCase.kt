package com.housing.movie.features.discount.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.service.DiscountService
import org.springframework.stereotype.Component

@Component
class GetAllDiscountsUseCase(
    private val discountService: DiscountService
) : BaseUseCase {
    override fun invoke(data: Any?): List<Discount> {
        return discountService.getAllDiscounts()
    }
}