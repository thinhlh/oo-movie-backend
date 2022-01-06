package com.housing.movie.features.discount.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.service.DiscountService
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetDiscountByIdUseCase(
    private val discountService: DiscountService
) : BaseUseCase {
    override fun invoke(data: Any?): Discount {
        return discountService.getDiscountById(data as UUID)
    }
}