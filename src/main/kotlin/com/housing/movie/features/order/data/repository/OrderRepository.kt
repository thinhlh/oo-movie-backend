package com.housing.movie.features.order.data.repository

import com.housing.movie.features.order.domain.entity.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : CrudRepository<Order, UUID> {


    fun findByUser_Id(id: UUID): List<Order>


    fun findByOrderTimeBetween(orderTimeStart: Calendar, orderTimeEnd: Calendar): List<Order>


    fun findByUser_IdIsAndOrderTimeBetween(id: UUID, orderTimeStart: Calendar, orderTimeEnd: Calendar): List<Order>

}