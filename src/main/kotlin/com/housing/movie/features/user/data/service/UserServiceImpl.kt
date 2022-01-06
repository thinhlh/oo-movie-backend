package com.housing.movie.features.user.data.service

import com.housing.movie.exceptions.CustomAuthenticationException
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.features.user.data.repository.UserRepository
import com.housing.movie.features.user.domain.entity.ApplicationUser
import com.housing.movie.features.user.domain.service.UserService
import com.housing.movie.utils.ServletHelper
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService, UserDetailsService {

    companion object {
        const val USER_NOT_FOUND = "User not found."
        const val INVALID_TOKEN = "Invalid token."
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw NotFoundException(USER_NOT_FOUND)

        val user: ApplicationUser =
            userRepository.findByUsernameAndEnabledIsTrue(username) ?: throw NotFoundException(USER_NOT_FOUND)

        // Add authorities of the requested user
        val authorities: MutableList<SimpleGrantedAuthority> = mutableListOf()
        authorities.add(SimpleGrantedAuthority(user.role.value))

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            authorities
        )
    }

    override fun findUserByUsername(username: String): ApplicationUser {
        return userRepository.findByUsername(username) ?: throw NotFoundException(USER_NOT_FOUND)
    }

    override fun getUsers(): List<ApplicationUser> {
        return userRepository.findAll().toList()
    }

    override fun findUserByJWTToken(): ApplicationUser {
        val username = ServletHelper.retrieveUsernameAndRoles {
            throw CustomAuthenticationException(INVALID_TOKEN)
        }.first

        return userRepository.findByUsername(username) ?: throw NotFoundException(USER_NOT_FOUND)
    }
}