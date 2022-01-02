package com.housing.movie.features.discount.data.repository

import com.housing.movie.features.discount.domain.entity.Discount
import org.springframework.data.repository.CrudRepository
import java.util.*

interface DiscountRepository : CrudRepository<Discount, UUID> {

    fun getDiscountByCode(code:String): Discount?


}