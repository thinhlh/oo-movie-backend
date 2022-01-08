package com.housing.movie.features.order.domain.service

import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.usecase.create_order.CreateOrderRequest
import java.util.*

interface OrderService {
    fun getOrdersUseCase(orderQueryParams: OrderQueryParams): List<Order>

    fun createOrder(createOrderRequest: CreateOrderRequest): Order

    fun getOrderById(orderId: UUID): Order
}