package com.housing.movie.features.plan.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.housing.movie.features.order.domain.entity.Order
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Plan(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var price: Double = 0.0,

    @JsonIgnore
    @OneToMany(mappedBy = "plan")
    val orders: MutableList<Order> = mutableListOf(),

    var enabled: Boolean = true
)