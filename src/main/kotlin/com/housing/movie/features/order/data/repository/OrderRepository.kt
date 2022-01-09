package com.housing.movie.features.order.data.repository

import com.housing.movie.features.order.domain.entity.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : CrudRepository<Order, UUID> {


    fun findByOrderTimeBetweenOrderByOrderTimeDesc(orderTimeStart: Calendar, orderTimeEnd: Calendar): List<Order>

    fun findByUser_IdOrderByOrderTimeDesc(id: UUID): List<Order>

    fun findByUser_IdAndOrderTimeBetweenOrderByOrderTimeDesc(
        id: UUID,
        orderTimeStart: Calendar,
        orderTimeEnd: Calendar
    ): List<Order>


    fun findByOrderByOrderTimeDesc(): List<Order>

}