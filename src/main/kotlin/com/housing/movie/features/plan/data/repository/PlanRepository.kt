package com.housing.movie.features.plan.data.repository

import com.housing.movie.features.plan.domain.entity.Plan
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanRepository : CrudRepository<Plan, UUID> {
}