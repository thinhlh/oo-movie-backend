package com.housing.movie.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.SecurityConfig
import com.housing.movie.features.user.domain.entity.Role
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.lang.Exception
import java.lang.NullPointerException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @property REQUEST_AUTHORIZATION_PATH indicate whether a method with path should be accessed with specific role
 * if @param List<Role> is null, this is used for all role (permit all)
 * if @param HttpMethod is null, this is used for all methods
 * */
object SecurityHelper {

    fun tokenHashAlgorithm(): Algorithm = Algorithm.HMAC256("housingmovie")

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

    fun authenticate(
        request: HttpServletRequest,
        response: HttpServletResponse,
        callback: (usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken) -> Unit
    ) {
        try {
            /**
             * We first retrieve the token, the achieve the username and roles which is hashed by the token
             * Then we will tell the spring context that the given username has a role like specified in authorities
             * */
            val token = getTokenFromHeader(request) ?: throw NullPointerException()

            val usernameAndRoles = retrieveUsernameAndRoles(token)
            val username = usernameAndRoles.first

            val authorities: List<SimpleGrantedAuthority> =
                usernameAndRoles.second.map { SimpleGrantedAuthority(it) }
            val usernamePasswordAuthentication = UsernamePasswordAuthenticationToken(username, null, authorities)


        } catch (e: Exception) {
            val errorResponse = BaseResponse.error(e.message.toString())
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.status = HttpStatus.UNAUTHORIZED.value()
            ObjectMapper().writeValue(response.outputStream, errorResponse)
        }
    }

    private fun retrieveUsernameAndRoles(token: String?): Pair<String, List<String>> {
        val jwtVerifier: JWTVerifier = JWT.require(tokenHashAlgorithm()).build()
        val decodedJWT: DecodedJWT = jwtVerifier.verify(token)

        val username = decodedJWT.subject

        // Actually in this application, we only use one role for each user
        val roles = decodedJWT.getClaim("roles").asList(String::class.java)

        return Pair(username, roles)
    }

    private fun getTokenFromHeader(request: HttpServletRequest): String? {
        val authorizationHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        return if (authorizationHeader == null) null
        else if (!authorizationHeader.startsWith("Bearer ")) null
        else authorizationHeader.substring("Bearer ".length)
    }

}