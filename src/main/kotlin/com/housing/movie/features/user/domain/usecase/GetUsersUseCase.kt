package com.housing.movie.features.user.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.user.domain.entity.ApplicationUser
import com.housing.movie.features.user.domain.service.UserService
import org.springframework.stereotype.Component

@Component
class GetUsersUseCase(
    private val userService: UserService
) : BaseUseCase {
    override fun invoke(data: Any?): List<ApplicationUser> {
        return userService.getUsers()
    }
}