package com.housing.movie.features.discount.domain.service

import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.usecase.create_discount.CreateDiscountRequest
import com.housing.movie.features.discount.domain.usecase.update_discount.UpdateDiscountRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
interface DiscountService {
    fun getAllDiscounts(): List<Discount>

    fun getDiscountById(id: UUID): Discount

    fun createDiscount(createDiscountRequest: CreateDiscountRequest): Discount

    fun getDiscountByCode(code: String): Discount

    fun updateDiscount(updateDiscountRequest: UpdateDiscountRequest): Discount

    fun deleteDiscount(id: UUID): Boolean

    fun enableDiscount(id: UUID): Discount

    fun useDiscount(id: UUID): Boolean
}