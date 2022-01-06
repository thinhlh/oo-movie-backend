package com.housing.movie.features.auth.domain.usecase.register

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.auth.domain.service.AuthService
import org.springframework.stereotype.Component

@Component
class RegisterUseCase(
    private val authService: AuthService
) : BaseUseCase {
    override fun invoke(data: Any?): Boolean {
        return authService.register(data as RegisterRequest)
    }
}