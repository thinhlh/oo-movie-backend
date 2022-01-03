package com.housing.movie.features.plan.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.plan.domain.entity.Plan
import com.housing.movie.features.plan.domain.usecase.DeletePlanUseCase
import com.housing.movie.features.plan.domain.usecase.EnablePlanUseCase
import com.housing.movie.features.plan.domain.usecase.GetPlansUseCase
import com.housing.movie.features.plan.domain.usecase.update_plan.UpdatePlanRequest
import com.housing.movie.features.plan.domain.usecase.update_plan.UpdatePlanUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty


@RestController
class PlanController(
    private val getPlansUseCase: GetPlansUseCase,
    private val deletePlanUseCase: DeletePlanUseCase,
    private val enablePlanUseCase: EnablePlanUseCase,
    private val updatePlanUseCase: UpdatePlanUseCase
) : BaseController() {

    @GetMapping("/plans")
    fun getPlans(): ResponseEntity<BaseResponse<List<Plan>>> {
        return successResponse(getPlansUseCase())
    }

    @DeleteMapping("/plan")
    fun deletePlan(@NotEmpty id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse(deletePlanUseCase(id))
    }

    @PutMapping("/plan/enable")
    fun enablePlan(@NotEmpty id: UUID): ResponseEntity<BaseResponse<Plan>> {
        return successResponse(enablePlanUseCase(id))
    }

    @PutMapping("/plan")
    fun updatePlan(@Valid @RequestBody updatePlanRequest: UpdatePlanRequest): ResponseEntity<BaseResponse<Plan>> {
        return successResponse(updatePlanUseCase(updatePlanRequest))
    }
}