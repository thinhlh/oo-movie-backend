package com.housing.movie.features.order.data.service

import com.housing.movie.exceptions.*
import com.housing.movie.features.discount.data.repository.DiscountRepository
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.order.data.repository.OrderRepository
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.order.domain.service.OrderQueryParams
import com.housing.movie.features.order.domain.service.OrderService
import com.housing.movie.features.order.domain.usecase.create_order.CreateOrderRequest
import com.housing.movie.features.plan.data.repository.PlanRepository
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.user.data.repository.UserRepository
import com.housing.movie.features.user.domain.entity.ApplicationUser
import com.housing.movie.utils.DateTimeHelper
import com.housing.movie.utils.ServletHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Month
import java.util.*
import kotlin.math.roundToLong

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val planRepository: PlanRepository,
    private val discountRepository: DiscountRepository
) : OrderService {

    companion object {
        const val USER_NOT_FOUND = "User not found."
        const val MOVIES_AND_PLAN_CANNOT_BE_NULL = "Movies or plan cannot be empty."
        const val MOVIES_AND_PLAN_CANNOT_EXISTS_AT_THE_SAME_TIME = "Movies and plan cannot exists at the same momment."
        const val MOVIE_NOT_FOUND = "Movie not found."
        const val PLAN_NOT_FOUND = "Plan not found."
        const val DISCOUNT_NOT_FOUND = "Discount not found."
        const val DISCOUNT_INVALIDATE = "Discount is unable to be used at the moment."
        const val ORDER_NOT_FOUND = "Order not found."
        const val USER_ALREADY_PURCHASED_PLAN = "User have already enrolled a plan."
        const val MOVIE_ALREADY_PURCHASED = "Movie already purchased: %s"
    }

    override fun getOrdersUseCase(orderQueryParams: OrderQueryParams): List<Order> {

        val orders = mutableListOf<Order>()
        val userId = orderQueryParams.user_id
        val fromTime = DateTimeHelper.timeStampToCalendar(orderQueryParams.from_time)
        val toTime = DateTimeHelper.timeStampToCalendar(orderQueryParams.to_time)

        if (userId == null) {

            if (fromTime == null && toTime == null) {

                orders.addAll(orderRepository.findByOrderByOrderTimeDesc())

            } else if (fromTime == null) {

                val startTime = Calendar.getInstance()
                startTime.set(1990, Month.JANUARY.value, 1)

                orders.addAll(orderRepository.findByOrderTimeBetweenOrderByOrderTimeDesc(startTime, toTime!!))
            } else if (toTime == null) {

                val endTime = Calendar.getInstance()

                orders.addAll(orderRepository.findByOrderTimeBetweenOrderByOrderTimeDesc(fromTime, endTime))
            } else {

                orders.addAll(orderRepository.findByOrderTimeBetweenOrderByOrderTimeDesc(fromTime, toTime))

            }
        } else {
            if (fromTime == null && toTime == null) {

                orders.addAll(orderRepository.findByUser_IdOrderByOrderTimeDesc(userId))

            } else if (fromTime == null) {

                val startTime = Calendar.getInstance()
                startTime.set(1990, Month.JANUARY.value, 1)

                orders.addAll(
                    orderRepository.findByUser_IdAndOrderTimeBetweenOrderByOrderTimeDesc(
                        userId,
                        startTime,
                        toTime!!
                    )
                )

            } else if (toTime == null) {

                val endTime = Calendar.getInstance()

                orders.addAll(
                    orderRepository.findByUser_IdAndOrderTimeBetweenOrderByOrderTimeDesc(
                        userId,
                        fromTime,
                        endTime
                    )
                )

            } else {

                orders.addAll(
                    orderRepository.findByUser_IdAndOrderTimeBetweenOrderByOrderTimeDesc(
                        userId,
                        fromTime,
                        toTime
                    )
                )

            }
        }

        return orders

    }

    override fun createOrder(createOrderRequest: CreateOrderRequest): Order {
        val username = ServletHelper.retrieveUsernameAndRoles {
            throw CustomAuthenticationException()
        }.first

        val user = validateUser(username)

        val movieIds = createOrderRequest.movieIds
        val planId = createOrderRequest.planId
        val discountCode = createOrderRequest.discountCode

        if (movieIds == null && planId == null) {
            throw MissingPropertyException(MOVIES_AND_PLAN_CANNOT_BE_NULL)
        } else if (movieIds != null && planId != null) {
            throw ConversionException(MOVIES_AND_PLAN_CANNOT_EXISTS_AT_THE_SAME_TIME)
        }

        val movies = validateMovies(movieIds, username)
        val plan = validatePlan(planId, username)
        val discount = validateDiscount(discountCode)
        val total = calculateOrderTotal(discount, plan, movies ?: emptyList())

        var order = Order(
            user = user,
            movies = movies?.toMutableList() ?: mutableListOf(),
            discount = discount,
            plan = plan,
            total = total
        )


        order = orderRepository.save(order)

        saveOrderToUser(user, order)
        saveOrderToMovies(movies, order)
        saveOrderToPlan(plan, order)
        saveOrderToDiscount(discount, order)

        return order


    }

    private fun calculateOrderTotal(discount: Discount?, plan: Plan?, movies: List<Movie>): Double {
        val discountValue = discount?.value ?: 0.0
        return if (plan != null) {
            val basePrice = plan.price
            val discountedPrice = basePrice - (discountValue * basePrice) / 100
            val finalPrice = if (discountedPrice < 0) 0.0 else discountedPrice

            round(finalPrice)
        } else {
            val basePrice = movies.size * (movies.firstOrNull() ?: Movie()).price
            val discountedPrice = basePrice - (discountValue * basePrice) / 100
            val finalPrice = if (discountedPrice < 0) 0.0 else discountedPrice

            round(finalPrice)
        }
    }

    private fun round(value: Double): Double {
        return (value * 100) / 100
    }

    override fun getOrderById(orderId: UUID): Order {
        return orderRepository.findByIdOrNull(orderId) ?: throw NotFoundException(ORDER_NOT_FOUND)
    }

    private fun saveOrderToUser(user: ApplicationUser, order: Order) {
        user.orders.add(order)
        userRepository.save(user)
    }

    private fun saveOrderToMovies(movies: List<Movie>?, order: Order) {

        movies ?: return

        movies.forEach { movie ->
            movie.orders.add(order)
        }

        movieRepository.saveAll(movies)
    }

    private fun saveOrderToPlan(plan: Plan?, order: Order) {
        plan ?: return

        plan.orders.add(order)
        planRepository.save(plan)
    }

    private fun saveOrderToDiscount(discount: Discount?, order: Order) {
        discount ?: return

        discount.orders.add(order)
        discount.remainingAmount -= 1
        discountRepository.save(discount)
    }

    private fun validateUser(username: String): ApplicationUser {

        return userRepository.findByUsername(username = username) ?: throw  NotFoundException(USER_NOT_FOUND)
    }

    private fun validateMovies(movieIds: List<UUID>?, username: String): List<Movie>? {
        movieIds ?: return null

        if (!isMovieAlreadyPurchased(movieIds, username)) {
            val movies = movieRepository.findByIdIsIn(movieIds)

            if (movies.isEmpty())
                throw NotFoundException(MOVIE_NOT_FOUND)

            return movies
        }

        return emptyList()
    }

    private fun isMovieAlreadyPurchased(movieIds: List<UUID>, username: String): Boolean {
        val ordersWithMovies = orderRepository.findByUser_UsernameAndMoviesIsNotEmptyOrderByOrderTimeDesc(username)

        val purchasedMoviesIds = mutableListOf<UUID>()

        ordersWithMovies.forEach { order ->
            purchasedMoviesIds.addAll(order.movies.map { it.id })
        }

        val overlapMovieIds = purchasedMoviesIds.intersect(movieIds)

        // The intersection between purchased movie ids and willing to purchase movieIds is the overlapping movie
        if (overlapMovieIds.isNotEmpty()) {
            throw ObjectAlreadyExistsException(
                MOVIE_ALREADY_PURCHASED.format(
                    overlapMovieIds.joinToString(separator = ",") { it.toString() },
                )
            )
        } else {
            return false
        }
    }

    private fun validatePlan(planId: UUID?, username: String): Plan? {
        planId ?: return null

        if (isUserAlreadyEnrolledAnyInExpiredPlan(username)) throw ObjectAlreadyExistsException(
            USER_ALREADY_PURCHASED_PLAN
        )

        return planRepository.findByIdOrNull(planId) ?: throw NotFoundException(PLAN_NOT_FOUND)
    }

    // If user already enrolled in a plan that has not expired yet -> false
    private fun isUserAlreadyEnrolledAnyInExpiredPlan(username: String): Boolean {
        val orders = orderRepository.findByUser_UsernameAndPlanIsNotNullOrderByOrderTimeDesc(username)

        return orders.any { order ->
            val expiredDate = order.orderTime
            expiredDate.add(Calendar.DATE, order.plan?.expired ?: 0)

            expiredDate.after(Calendar.getInstance())
        }
    }

    private fun validateDiscount(discountCode: String?): Discount? {
        discountCode ?: return null

        return discountRepository.findByCodeAndTimeEndIsAfterAndRemainingAmountIsGreaterThanAndEnabledIsTrue(
            discountCode,
            Calendar.getInstance()
        )
            ?: throw NotFoundException(
                DISCOUNT_INVALIDATE
            )
    }
}