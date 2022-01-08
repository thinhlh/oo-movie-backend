package com.housing.movie.features.order.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderService
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetOrderUseCase(
    private val orderService: OrderService
) : BaseUseCase {
    override fun invoke(data: Any?): Order {
        return orderService.getOrderById(data as UUID)
    }
}