package com.housing.movie.features.plan.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Plan(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var price: Double = 0.0,

    var enabled: Boolean = true
)