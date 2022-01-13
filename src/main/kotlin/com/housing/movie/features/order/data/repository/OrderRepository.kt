package com.housing.movie.features.order.data.repository

import com.housing.movie.features.order.domain.entity.Order
import org.springframework.data.domain.Sort
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


    fun findByPlan_IdAndOrderTimeBetween(planId: UUID, orderTimeStart: Calendar, orderTimeEnd: Calendar): List<Order>


    fun findByPlan_Id(planId: UUID): List<Order>


    fun findByUser_Username(username: String): List<Order>

    fun findByUser_UsernameAndPlanIsNotNullOrderByOrderTimeDesc(username: String): List<Order>


    fun findByUser_UsernameAndMoviesIsNotEmptyOrderByOrderTimeDesc(username: String): List<Order>

    fun findByUser_UsernameAndMoviesIsNotEmptyAndMovies_IdIsInOrderByOrderTimeDesc(
        username: String,
        movieIds: List<UUID>
    ): List<Order>

}