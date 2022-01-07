package com.housing.movie.features.order.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderQueryParams
import com.housing.movie.features.order.domain.usecase.get_orders.GetOrdersUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class OrderController(
    private val ordersUseCase: GetOrdersUseCase
) : BaseController() {
    @GetMapping("orders")
    fun getAllOrders(orderQueryParams: OrderQueryParams): ResponseEntity<BaseResponse<List<Order>>> {
        return successResponse(ordersUseCase(orderQueryParams))
    }
}