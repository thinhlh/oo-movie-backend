package com.housing.movie.features.auth.domain.usecase.register

import com.housing.movie.features.user.domain.entity.ApplicationUser
import com.housing.movie.features.user.domain.entity.Role
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RegisterRequest(

    @NotBlank
    val username: String,

    @NotBlank
    val password: String,

    val fullname: String = "",

    @NotNull
    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.Subscriber,

    ) {
    fun toUser(): ApplicationUser {
        return ApplicationUser(
            username = username,
            password = password,
            fullname = fullname,
            role = role
        )
    }
}
