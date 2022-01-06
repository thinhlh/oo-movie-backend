package com.housing.movie.features.user.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.user.domain.entity.ApplicationUser
import com.housing.movie.features.user.domain.usecase.GetUserUseCase
import com.housing.movie.features.user.domain.usecase.GetUsersUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class UserController(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseController() {

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<BaseResponse<List<ApplicationUser>>> {
        return successResponse(getUsersUseCase())
    }

    @GetMapping("/user")
    fun getUser(): ResponseEntity<BaseResponse<ApplicationUser>> {
        return successResponse(getUserUseCase())
    }
}