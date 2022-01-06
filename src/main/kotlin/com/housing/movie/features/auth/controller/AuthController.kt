package com.housing.movie.features.auth.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.features.auth.domain.usecase.RefreshTokenUseCase
import com.housing.movie.features.auth.domain.usecase.register.RegisterRequest
import com.housing.movie.features.auth.domain.usecase.register.RegisterUseCase
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
class AuthController(
    private val registerUseCase: RegisterUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : BaseController() {

    @PostMapping("/register")
    fun register(@RequestBody @Valid registerRequest: RegisterRequest): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse(registerUseCase(registerRequest))
    }

    @GetMapping("/token/refresh")
    fun refreshToken(): ResponseEntity<BaseResponse<Tokens>> {
        return successResponse(refreshTokenUseCase())
    }
}