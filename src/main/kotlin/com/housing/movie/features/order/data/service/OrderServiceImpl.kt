package com.housing.movie.features.order.data.service

import com.housing.movie.features.order.data.repository.OrderRepository
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderQueryParams
import com.housing.movie.features.order.domain.service.OrderService
import com.housing.movie.features.user.data.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository
) : OrderService {
    override fun getOrdersUseCase(orderQueryParams: OrderQueryParams): List<Order> {

        var orders: List<Order>
        val userId = orderQueryParams.user_id
        val fromTime = orderQueryParams.from_time
        val toTime = orderQueryParams.to_time


        if (userId == null) {

            if (fromTime == null && toTime == null) {

            } else if (fromTime == null) {

            } else if (toTime == null) {

            } else {

            }
        } else {
            if (fromTime == null && toTime == null) {

            } else if (fromTime == null) {

            } else if (toTime == null) {

            } else {

            }
        }

        return arrayListOf()

    }

}