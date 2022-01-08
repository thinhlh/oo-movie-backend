package com.housing.movie.features.order.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderQueryParams
import com.housing.movie.features.order.domain.usecase.create_order.CreateOrderRequest
import com.housing.movie.features.order.domain.usecase.create_order.CreateOrderUseCase
import com.housing.movie.features.order.domain.usecase.get_orders.GetOrdersUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val createOrderUseCase: CreateOrderUseCase
) : BaseController() {
    @GetMapping("/orders")
    fun getAllOrders(orderQueryParams: OrderQueryParams): ResponseEntity<BaseResponse<List<Order>>> {
        return successResponse(getOrdersUseCase(orderQueryParams))
    }

    @PostMapping("/order")
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest): ResponseEntity<BaseResponse<Order>> {
        return successResponse(createOrderUseCase(createOrderRequest))
    }
}