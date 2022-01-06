package com.housing.movie.features.auth.domain.service

import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.features.auth.domain.usecase.register.RegisterRequest

interface AuthService {
    fun register(registerRequest: RegisterRequest): Boolean

    fun refreshToken(): Tokens
}