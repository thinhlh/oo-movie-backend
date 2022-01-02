package com.housing.movie.features

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Discount(
    @Id
    @GeneratedValue
    private val id: UUID = UUID.randomUUID()
)