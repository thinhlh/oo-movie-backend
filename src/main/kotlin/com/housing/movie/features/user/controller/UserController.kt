package com.housing.movie.features.user.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.user.domain.entity.ApplicationUser
import com.housing.movie.features.user.domain.usecase.GetUsersUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val getUsersUseCase: GetUsersUseCase,
) : BaseController() {

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<BaseResponse<List<ApplicationUser>>> {
        return successResponse(getUsersUseCase())
    }
}