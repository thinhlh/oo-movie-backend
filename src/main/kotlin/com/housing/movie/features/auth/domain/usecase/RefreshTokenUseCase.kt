package com.housing.movie.features.auth.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.features.auth.domain.service.AuthService
import org.springframework.stereotype.Component

@Component
class RefreshTokenUseCase(
    private val authService: AuthService
) : BaseUseCase {
    override fun invoke(data: Any?): Tokens {
        return authService.refreshToken()
    }
}