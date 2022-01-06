package com.housing.movie.features.user.data.repository

import com.housing.movie.features.user.domain.entity.ApplicationUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<ApplicationUser, UUID> {
    fun findByUsername(username: String): ApplicationUser?

    fun findByUsernameAndEnabledIsTrue(username: String): ApplicationUser?

    fun existsByUsername(username: String): Boolean
}