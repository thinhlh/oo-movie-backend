package com.housing.movie.features.user.domain.service

import com.housing.movie.features.user.domain.entity.ApplicationUser

interface UserService {
    fun findUserByUsername(username: String): ApplicationUser
    fun getUsers(): List<ApplicationUser>
    fun findUserByJWTToken(): ApplicationUser
}