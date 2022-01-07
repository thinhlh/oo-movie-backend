package com.housing.movie.features.order.domain.usecase.get_orders

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderQueryParams
import com.housing.movie.features.order.domain.service.OrderService
import org.springframework.stereotype.Component

@Component
class GetOrdersUseCase(
    private val orderService: OrderService
) : BaseUseCase {
    override fun invoke(data: Any?): List<Order> {
        return orderService.getOrdersUseCase(data as OrderQueryParams)
    }
}