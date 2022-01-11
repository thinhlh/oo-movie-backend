package com.housing.movie.features.plan.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.features.order.data.repository.OrderRepository
import com.housing.movie.features.order.domain.entity.Order
import com.housing.movie.features.plan.data.repository.PlanRepository
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.plan.domain.service.PlanService
import com.housing.movie.features.plan.domain.usecase.update_plan.UpdatePlanRequest
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanQueryParams
import com.housing.movie.features.statistic.domain.usecase.top_purchase_plan.TopPurchasePlanResponse
import com.housing.movie.utils.DateTimeHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PlanServiceImpl(
    private val planRepository: PlanRepository,
    private val orderRepository: OrderRepository,
) : PlanService {

    companion object {
        const val PLAN_NOT_FOUND = "Plan not found."
    }

    override fun getPlans(): List<Plan> {
        return planRepository.findAll().toList()
    }

    @Transactional
    override fun deletePlan(id: UUID): Boolean {
        val plan = planRepository.findByIdOrNull(id) ?: throw NotFoundException(PLAN_NOT_FOUND)

        plan.enabled = false

        return true
    }

    @Transactional
    override fun enablePlan(id: UUID): Plan {
        val plan = planRepository.findByIdOrNull(id) ?: throw NotFoundException(PLAN_NOT_FOUND)

        plan.enabled = false

        return plan
    }

    override fun updatePlan(updatePlanRequest: UpdatePlanRequest): Plan {
        var plan = planRepository.findByIdOrNull(updatePlanRequest.id) ?: throw NotFoundException(PLAN_NOT_FOUND)

        plan = updatePlanRequest.updatePlan(plan)

        return planRepository.save(plan)
    }

    override fun topPurchasePlan(topPurchasePlanQueryParams: TopPurchasePlanQueryParams): List<TopPurchasePlanResponse> {
        val startDate =
            DateTimeHelper.stringToCalendar(topPurchasePlanQueryParams.start_date, DateTimeHelper.Format.DD__MM__YYYY)

        val endDate =
            DateTimeHelper.stringToCalendar(topPurchasePlanQueryParams.end_date, DateTimeHelper.Format.DD__MM__YYYY)

        val plans = planRepository.findAll().toList()

        val defaultStartDate = Calendar.getInstance().also { it.set(1990, Calendar.JANUARY, 1) }
        val defaultEndDate = Calendar.getInstance()

        val planAndTotalPurchaseValue = mutableMapOf<Plan, Double>()

        plans.forEach { plan ->
            val orders = mutableListOf<Order>()
            if ((startDate == null) && (endDate == null)) {

                orders.addAll(orderRepository.findByPlan_Id(planId = plan.id))

            } else if (startDate == null) {

                orders.addAll(orderRepository.findByPlan_IdAndOrderTimeBetween(plan.id, defaultStartDate, endDate!!))

            } else if (endDate == null) {

                orders.addAll(orderRepository.findByPlan_IdAndOrderTimeBetween(plan.id, startDate, defaultEndDate))

            } else {

                orders.addAll(orderRepository.findByPlan_IdAndOrderTimeBetween(plan.id, startDate, endDate))

            }

            planAndTotalPurchaseValue[plan] = orders.sumOf { order -> order.total }
        }

        val result = planAndTotalPurchaseValue.toList().sortedByDescending { (_, totalValue) -> totalValue }

        return result.map { (plan, totalValue) ->
            TopPurchasePlanResponse(
                planId = plan.id,
                title = plan.title,
                total = totalValue
            )
        }
    }
}