package com.housing.movie.features.order.domain.usecase.create_order

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderService
import org.springframework.stereotype.Component

@Component
class CreateOrderUseCase(
    private val orderService: OrderService
) : BaseUseCase {
    override fun invoke(data: Any?): Order {
        return orderService.createOrder(data as CreateOrderRequest)
    }
}