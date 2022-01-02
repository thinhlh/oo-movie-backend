package com.housing.movie.features.discount.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectAlreadyExistsException
import com.housing.movie.exceptions.RunOutOfUsageException
import com.housing.movie.features.discount.data.repository.DiscountRepository
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.service.DiscountService
import com.housing.movie.features.discount.domain.usecase.create_discount.CreateDiscountRequest
import com.housing.movie.features.discount.domain.usecase.update_discount.UpdateDiscountRequest
import org.hibernate.annotations.NotFound
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DiscountServiceImpl(
    private val discountRepository: DiscountRepository
) : DiscountService {

    companion object {
        const val DISCOUNT_NOT_FOUND = "Discount not found."
        const val DISCOUNT_CODE_EXISTS = "Discount code already exists."
        const val RUN_OUT_OF_DISCOUNT = "Discount is unavailable."
    }

    override fun getAllDiscounts(): List<Discount> {
        return discountRepository.findAll().toList()
    }

    override fun getDiscountById(id: UUID): Discount {
        return discountRepository.findByIdOrNull(id) ?: throw  NotFoundException(DISCOUNT_NOT_FOUND)
    }

    override fun createDiscount(createDiscountRequest: CreateDiscountRequest): Discount {
        if (discountRepository.getDiscountByCode(createDiscountRequest.code) != null) {
            throw ObjectAlreadyExistsException(DISCOUNT_CODE_EXISTS)
        } else {
            val discount: Discount = createDiscountRequest.toDiscount()
            discountRepository.save(discount)
            return discount
        }
    }

    override fun getDiscountByCode(code: String): Discount {
        return discountRepository.getDiscountByCode(code) ?: throw NotFoundException(DISCOUNT_NOT_FOUND)
    }

    @Transactional
    override fun updateDiscount(updateDiscountRequest: UpdateDiscountRequest): Discount {
        var oldDiscount =
            discountRepository.findByIdOrNull(updateDiscountRequest.id) ?: throw NotFoundException(DISCOUNT_NOT_FOUND)

        oldDiscount = updateDiscountRequest.updateDiscount(oldDiscount)

        discountRepository.save(oldDiscount)

        return oldDiscount
    }

    @Transactional
    override fun deleteDiscount(id: UUID): Boolean {
        val discount = discountRepository.findByIdOrNull(id) ?: throw NotFoundException(DISCOUNT_NOT_FOUND)

        discount.enabled = false

        return true
    }

    @Transactional
    override fun enableDiscount(id: UUID): Discount {
        val discount = discountRepository.findByIdOrNull(id) ?: throw NotFoundException(DISCOUNT_NOT_FOUND)

        discount.enabled = false

        return discount
    }

    @Transactional
    override fun useDiscount(id: UUID): Boolean {
        val discount = discountRepository.findByIdOrNull(id) ?: throw NotFoundException(DISCOUNT_NOT_FOUND)

        if (discount.remainingAmount <= 0) throw RunOutOfUsageException(RUN_OUT_OF_DISCOUNT)

        discount.remainingAmount -= 1

        return true
    }


}