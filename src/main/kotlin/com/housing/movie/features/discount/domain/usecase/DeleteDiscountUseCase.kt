package com.housing.movie.features.discount.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.discount.domain.service.DiscountService
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeleteDiscountUseCase(
    private val discountService: DiscountService
) : BaseUseCase {
    override fun invoke(data: Any?): Boolean {
        return discountService.deleteDiscount(data as UUID)
    }
}