package com.housing.movie.features.user.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.housing.movie.features.movie.domain.entity.Movie
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

//@Entity
//class User(
//
//    @Id
//    @GeneratedValue
//    val id: UUID = UUID.randomUUID(),
//
//    @NotBlank
//    var name: String = "",
//
//    @NotBlank
//    val username: String = "",
//
//    @NotNull
//    val role: Role = Role.Subscriber,
//
//    @Temporal(value = TemporalType.DATE)
//    val birthday: Calendar? = null,
//
//    var enabled: Boolean = true,
//
//    )