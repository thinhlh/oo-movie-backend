package com.housing.movie.features.plan.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.features.plan.data.repository.PlanRepository
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.plan.domain.service.PlanService
import com.housing.movie.features.plan.domain.usecase.update_plan.UpdatePlanRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PlanServiceImpl(
    private val planRepository: PlanRepository
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
}