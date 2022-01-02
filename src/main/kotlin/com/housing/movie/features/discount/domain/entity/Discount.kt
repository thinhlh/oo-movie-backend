package com.housing.movie.features.discount.domain.entity

import java.util.*
import javax.persistence.*

@Entity
class Discount(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    var name: String = "",

    var description: String = "",

    var remainingAmount: Int = 0,

    @Temporal(value = TemporalType.DATE)
    var timeBegin: Calendar? = null,

    @Temporal(value = TemporalType.DATE)
    var timeEnd: Calendar? = null,

    var value: Double = 0.0,

    var code: String = com.housing.movie.utils.StringHelper.randomString(10),

    var enabled: Boolean = true
)