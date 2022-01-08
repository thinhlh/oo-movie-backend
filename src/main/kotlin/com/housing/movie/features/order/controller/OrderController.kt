package com.housing.movie.features.order.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderQueryParams
import com.housing.movie.features.order.domain.usecase.GetOrderUseCase
import com.housing.movie.features.order.domain.usecase.create_order.CreateOrderRequest
import com.housing.movie.features.order.domain.usecase.create_order.CreateOrderUseCase
import com.housing.movie.features.order.domain.usecase.get_orders.GetOrdersUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@RestController
class OrderController(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val getOrderUseCase: GetOrderUseCase,
    private val createOrderUseCase: CreateOrderUseCase
) : BaseController() {
    @GetMapping("/orders")
    fun getAllOrders(orderQueryParams: OrderQueryParams): ResponseEntity<BaseResponse<List<Order>>> {
        return successResponse(getOrdersUseCase(orderQueryParams))
    }

    @GetMapping("/order")
    fun getOrder(@RequestParam @NotBlank id: UUID): ResponseEntity<BaseResponse<Order>> {
        return successResponse(getOrderUseCase(id))
    }


    @PostMapping("/order")
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest): ResponseEntity<BaseResponse<Order>> {
        return successResponse(createOrderUseCase(createOrderRequest))
    }
}