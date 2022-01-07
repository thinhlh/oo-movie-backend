package com.housing.movie.features.order.domain.service

import com.housing.movie.features.order.domain.entity.Order

interface OrderService {
    fun getOrdersUseCase(orderQueryParams: OrderQueryParams): List<Order>
}