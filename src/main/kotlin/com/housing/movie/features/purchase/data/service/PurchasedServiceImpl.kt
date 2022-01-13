package com.housing.movie.features.purchase.data.service

import com.housing.movie.exceptions.CustomAuthenticationException
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.SuccessIsFalseException
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.order.data.repository.OrderRepository
import com.housing.movie.features.purchase.domain.service.PurchasedService
import com.housing.movie.features.purchase.domain.usecase.current_enrolled_plan_status.GetCurrentEnrolledPlanStatusResponse
import com.housing.movie.utils.ServletHelper
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class PurchasedServiceImpl(
    private val orderRepository: OrderRepository
) : PurchasedService {

    companion object {
        const val NO_PLAN_PURCHASED = "User have not purchased any plan."
        const val PLAN_EXPIRED = "All user's plan are expired."
    }

    override fun purchasedMovies(): List<Movie> {

        val username = ServletHelper.retrieveUsernameAndRoles {
            throw CustomAuthenticationException()
        }.first

        val orders = orderRepository.findByUser_Username(username)

        var movies = mutableListOf<Movie>()
        orders.forEach { order ->
            movies.addAll(order.movies)
        }

        movies = movies.distinctBy { movie ->
            movie.id
        }.toMutableList()

        return movies
    }

    override fun getCurrentEnrolledPlanStatus(): GetCurrentEnrolledPlanStatusResponse {
        val username = ServletHelper.retrieveUsernameAndRoles {
            throw CustomAuthenticationException()
        }.first

        // Orders with plan
        val planOrders = orderRepository.findByUser_UsernameAndPlanIsNotNullOrderByOrderTimeDesc(username)

        if (planOrders.isEmpty()) {
            throw SuccessIsFalseException(NO_PLAN_PURCHASED)
        }

        val lastOrder = planOrders.last()

        val expiredDate = lastOrder.orderTime
        expiredDate.add(Calendar.DATE, lastOrder.plan?.expired ?: 0)

        if (expiredDate.after(Calendar.getInstance())) {
            return GetCurrentEnrolledPlanStatusResponse(lastOrder.plan, expiredDate)
        } else {
            throw SuccessIsFalseException(PLAN_EXPIRED)
        }
    }
}