package com.housing.movie.features.auth.data.service

import com.housing.movie.features.auth.domain.service.AuthService
import com.housing.movie.features.auth.domain.usecase.register.RegisterRequest
import com.housing.movie.features.user.data.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
) : AuthService {
    override fun register(registerRequest: RegisterRequest): Boolean {
        val user = registerRequest.toUser()
        user.password = encryptPassword(user.password)

        userRepository.save(user)

        return true
    }

    private fun encryptPassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }
}