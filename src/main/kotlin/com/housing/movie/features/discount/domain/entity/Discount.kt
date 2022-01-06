package com.housing.movie.features.discount.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
class Discount(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    var name: String = "",

    var description: String = "",

    @JsonProperty(value="remaining_amount")
    var remainingAmount: Int = 0,

    @Temporal(value = TemporalType.DATE)
    @JsonProperty(value = "time_begin")
    var timeBegin: Calendar? = null,

    @Temporal(value = TemporalType.DATE)
    @JsonProperty(value = "time_end")
    var timeEnd: Calendar? = null,

    var value: Double = 0.0,

    var code: String = com.housing.movie.utils.StringHelper.randomString(10),

    var enabled: Boolean = true
)