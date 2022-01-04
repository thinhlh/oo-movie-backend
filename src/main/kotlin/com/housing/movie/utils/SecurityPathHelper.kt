package com.housing.movie.utils

import com.housing.movie.features.user.domain.entity.Role
import org.springframework.http.HttpMethod

/**
 * @property REQUEST_AUTHORIZATION_PATH indicate whether a method with path should be accessed with specific role
 * if @param List<Role> is null, this is used for all role (permit all)
 * if @param HttpMethod is null, this is used for all methods
 * */
object SecurityPathHelper {
    val REQUEST_AUTHORIZATION_PATH = mapOf<Pair<String, List<HttpMethod>?>, List<Role>?>(

        Pair("/login", null)
                to null,

        Pair("/register", listOf(HttpMethod.POST))
                to null,

        Pair("/users", null)
                to listOf(Role.Administrator),

        Pair("/ping", listOf(HttpMethod.GET))
                to null,

        Pair("/ping/admin", listOf(HttpMethod.GET))
                to listOf(Role.Administrator),

        Pair("/ping/subscriber", listOf(HttpMethod.GET))
                to listOf(Role.Subscriber)

    )

}