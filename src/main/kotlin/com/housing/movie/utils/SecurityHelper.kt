package com.housing.movie.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.filter.CustomAuthenticationFilter
import com.housing.movie.exceptions.CustomAuthenticationException
import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.features.user.domain.entity.Role
import com.housing.movie.utils.SecurityHelper.REQUEST_AUTHORIZATION_PATH
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
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
                to listOf(Role.Subscriber)

    )

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
            val token = getTokenFromHeader(request) ?: throw NullPointerException()

            val usernameAndRoles = retrieveUsernameAndRoles(token)
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
        refreshToken: String?,
        callback: (username: String) -> Role
    ): Tokens {
        try {
            val decodedRefreshToken = decodeToken(refreshToken) ?: throw CustomAuthenticationException(INVALID_TOKEN)
            val usernameAndRoles = retrieveUsernameAndRoles(decodedRefreshToken)

            val role = callback(usernameAndRoles.first)

            val currentRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes)
                .request

            val accessToken = generateToken(
                username = usernameAndRoles.first,
                timeout = CustomAuthenticationFilter.ACCESS_TOKEN_TIMEOUT,
                authorities = listOf(role.value),
                requestUrl = currentRequest.requestURL.toString()
            )

            return Tokens(accessToken, decodedRefreshToken)
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

    // Retrieve username and role from JWT token
    private fun retrieveUsernameAndRoles(token: String?): Pair<String, List<String>> {
        val jwtVerifier: JWTVerifier = JWT.require(tokenHashAlgorithm()).build()
        val decodedJWT: DecodedJWT = jwtVerifier.verify(token)

        val username = decodedJWT.subject

        // Actually in this application, we only use one role for each user
        val roles = decodedJWT.getClaim("roles").asList(String::class.java)

        return Pair(username, roles)
    }

    // Decode token from authorization header
    private fun getTokenFromHeader(request: HttpServletRequest): String? {
        val authorizationHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        return decodeToken(authorizationHeader)
    }

    // Decode token from raw token to extracted token
    private fun decodeToken(refreshToken: String?): String? {
        return if (refreshToken == null) null
        else if (!refreshToken.startsWith("Bearer ")) null
        else refreshToken.substring("Bearer ".length)
    }
}