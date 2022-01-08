package com.housing.movie.features.user.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.housing.movie.features.comment.domain.entity.Comment
import com.housing.movie.features.common.entity.extended_response.UserInfo
import com.housing.movie.features.order.domain.entity.Order
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@JsonIgnoreProperties(allowGetters = false, allowSetters = true, value = ["password"])
class ApplicationUser(

    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(unique = true)
    var username: String = "",

    var fullname: String = "",

    @NotBlank
    var password: String = "",

    @NotNull
    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.Subscriber,

    var enabled: Boolean = true,

    @JsonIgnore
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    val comments: MutableList<Comment> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val orders: MutableList<Order> = mutableListOf()

) {
    fun toUserInfo(): UserInfo {
        return UserInfo(id, username, fullname)
    }
}