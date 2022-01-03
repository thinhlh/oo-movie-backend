package com.housing.movie.features.discount.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.discount.domain.entity.Discount
import com.housing.movie.features.discount.domain.usecase.*
import com.housing.movie.features.discount.domain.usecase.create_discount.CreateDiscountRequest
import com.housing.movie.features.discount.domain.usecase.create_discount.CreateDiscountUseCase
import com.housing.movie.features.discount.domain.usecase.update_discount.UpdateDiscountRequest
import com.housing.movie.features.discount.domain.usecase.update_discount.UpdateDiscountUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
class DiscountController(
    private val getAllDiscountsUseCase: GetAllDiscountsUseCase,
    private val getDiscountByIdUseCase: GetDiscountByIdUseCase,
    private val getDiscountByCodeUseCase: GetDiscountByCodeUseCase,
    private val createDiscountUseCase: CreateDiscountUseCase,
    private val updateDiscountUseCase: UpdateDiscountUseCase,
    private val deleteDiscountUseCase: DeleteDiscountUseCase,
    private val enableDiscountUseCase: EnableDiscountUseCase,
    private val useDiscountUseCase: UseDiscountUseCase,
) : BaseController() {

    @GetMapping("/discounts")
    fun getAllDiscounts(): ResponseEntity<BaseResponse<List<Discount>>> {
        return successResponse(getAllDiscountsUseCase())
    }

    @GetMapping("/discount/id")
    fun getDiscountById(@NotEmpty @RequestParam id: UUID): ResponseEntity<BaseResponse<Discount>> {
        return successResponse(getDiscountByIdUseCase(id))
    }

    @GetMapping("/discount/code")
    fun getDiscountByCode(@NotEmpty @RequestParam code: String): ResponseEntity<BaseResponse<Discount>> {
        return successResponse(getDiscountByCodeUseCase(code))
    }

    @PostMapping("/discount")
    fun createDiscount(@Valid @RequestBody createDiscountRequest: CreateDiscountRequest): ResponseEntity<BaseResponse<Discount>> {
        return successResponse(createDiscountUseCase(createDiscountRequest))
    }

    @PutMapping("/discount")
    fun updateDiscount(@Valid @RequestBody updateDiscountRequest: UpdateDiscountRequest): ResponseEntity<BaseResponse<Discount>> {
        return successResponse(updateDiscountUseCase(updateDiscountRequest))
    }

    @DeleteMapping("/discount")
    fun deleteDiscount(@NotEmpty @RequestParam id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse(deleteDiscountUseCase(id))
    }

    @PutMapping("/discount/enable")
    fun enableDiscount(@NotEmpty @RequestParam id: UUID): ResponseEntity<BaseResponse<Discount>> {
        return successResponse(enableDiscountUseCase(id))
    }

    @PostMapping("/discount/use")
    fun useDiscount(@NotEmpty @RequestParam id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse(useDiscountUseCase(id))
    }
}