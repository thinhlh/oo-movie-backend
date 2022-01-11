package com.housing.movie.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.filter.CustomAuthenticationFilter
import com.housing.movie.exceptions.CustomAuthenticationException
import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.features.user.domain.entity.Role
import com.housing.movie.utils.SecurityHelper.REQUEST_AUTHORIZATION_PATH
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @property REQUEST_AUTHORIZATION_PATH indicate whether a method with path should be accessed with specific role
 * if @param List<Role> is null, this is used for all role (permit all)
 * if @param HttpMethod is null, this is used for all methods
 * */
object SecurityHelper {

    private const val INVALID_TOKEN = "Invalid token."

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
                to listOf(Role.Subscriber),

        Pair("/docs", null) to null,

        Pair("/movies/**", listOf(HttpMethod.GET)) to null,

        Pair("/movie", listOf(HttpMethod.GET)) to null

    )

    /*
    * Authenticate when user call api
    * */
    fun authenticate(
        request: HttpServletRequest,
        response: HttpServletResponse,
        callback: (authResult: UsernamePasswordAuthenticationToken) -> Unit
    ) {
        try {
            /**
             * We first retrieve the token, the achieve the username and roles which is hashed by the token
             * Then we will tell the spring context that the given username has a role like specified in authorities
             * */

            val usernameAndRoles = ServletHelper.retrieveUsernameAndRoles(request.getHeader(AUTHORIZATION)) {
                throw CustomAuthenticationException(INVALID_TOKEN)
            }
            val username = usernameAndRoles.first

            val authorities: List<SimpleGrantedAuthority> =
                usernameAndRoles.second.map { SimpleGrantedAuthority(it) }
            val usernamePasswordAuthentication = UsernamePasswordAuthenticationToken(username, null, authorities)

            callback(usernamePasswordAuthentication)

        } catch (e: Exception) {
            errorResponse(response, e)
        }
    }

    /** Use this method for authenticate user using
     * @param refreshToken, which will execute the callback to the UserService to find the user
     * After retrieved the user this will get the authorities (role) and create new access token
     */
    fun authenticate(
        findRoleByUsername: (username: String) -> Role
    ): Tokens {
        try {
            val usernameAndRoles = ServletHelper.retrieveUsernameAndRoles(invalidTokenCallback = {
                throw CustomAuthenticationException(INVALID_TOKEN)
            })

            val role = findRoleByUsername(usernameAndRoles.first)

            val currentRequest: HttpServletRequest = ServletHelper.request()

            val accessToken = generateToken(
                username = usernameAndRoles.first,
                timeout = CustomAuthenticationFilter.ACCESS_TOKEN_TIMEOUT,
                authorities = listOf(role.value),
                requestUrl = currentRequest.requestURL.toString()
            )

            // Current JWT token is refresh token
            return Tokens(accessToken, ServletHelper.getRawAuthorizationJWTToken().toString())
        } catch (e: Exception) {
            throw CustomAuthenticationException(message = e.message)
        }
    }

    // Generate JWT token
    fun generateToken(
        username: String?,
        timeout: Int,
        authorities: List<String>?,
        requestUrl: String
    ): String {
        return JWT
            .create()
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + timeout))
            .withIssuer(requestUrl)
            .withClaim(CustomAuthenticationFilter.claim, authorities)
            .sign(tokenHashAlgorithm())
    }

    private fun errorResponse(response: HttpServletResponse, e: Exception) {
        val errorResponse = BaseResponse.error(e.message.toString())
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()
        ObjectMapper().writeValue(response.outputStream, errorResponse)
    }
}