package com.housing.movie.features.auth.data.service

import com.housing.movie.exceptions.ConversionException
import com.housing.movie.exceptions.CustomAuthenticationException
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectAlreadyExistsException
import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.features.auth.domain.service.AuthService
import com.housing.movie.features.auth.domain.usecase.register.RegisterRequest
import com.housing.movie.features.user.data.repository.UserRepository
import com.housing.movie.utils.SecurityHelper
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
) : AuthService {

    companion object {
        const val USER_ALREADY_EXISTS = "User already exists."
        const val USER_NOT_FOUND = "User not found."
    }

    override fun register(registerRequest: RegisterRequest): Boolean {

        if (userRepository.existsByUsername(registerRequest.username)) throw ObjectAlreadyExistsException(
            USER_ALREADY_EXISTS
        )

        val user = registerRequest.toUser()
        user.password = encryptPassword(user.password)

        userRepository.save(user)

        return true
    }

    override fun refreshToken(): Tokens {
        return SecurityHelper.authenticate { username ->
            (userRepository.findByUsernameAndEnabledIsTrue(username) ?: throw NotFoundException(USER_NOT_FOUND)).role
        }
    }

    private fun encryptPassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }
}