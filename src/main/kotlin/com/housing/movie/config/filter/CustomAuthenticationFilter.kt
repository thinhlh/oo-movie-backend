package com.housing.movie.config.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.SecurityConfig
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import java.util.logging.Logger
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Authentication filter by username and password
 * */
class CustomAuthenticationFilter(
    authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    companion object {
        const val claim: String = "roles"
        const val ACCESS_TOKEN: String = "access_token"
        const val REFRESH_TOKEN: String = "refresh_token"
        const val ACCESS_TOKEN_TIMEOUT = 60 * 60 * 1000 // 1 hour
        const val REFRESH_TOKEN_TIMEOUT = 7 * 24 * 60 * 60 * 1000 // 1 week
    }


    private val algorithm: Algorithm = SecurityConfig.tokenHashAlgorithm()

    // This is called whenever user call in the /login api
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val username = request?.getParameter("username")
        val password = request?.getParameter("password")

        Logger.getAnonymousLogger().info("Username:${username},password: ${password}")

        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(username, password)

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken)
    }


    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authentication: Authentication?
    ) {
        // authentication principle is success fully authenticated object
        val user: User? = authentication?.principal as User?


        // One hour expire
        val accessToken = JWT
            .create()
            .withSubject(user?.username)
            .withExpiresAt(Date(System.currentTimeMillis() + ACCESS_TOKEN_TIMEOUT))
            .withIssuer(request?.requestURL.toString())
            .withClaim(claim, user?.authorities?.map { it -> it.authority })
            .sign(algorithm)

        // One day expire
        val refreshToken = JWT
            .create()
            .withSubject(user?.username)
            .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_TOKEN_TIMEOUT))
            .withIssuer(request?.requestURL.toString())
            .withClaim(claim, user?.authorities?.map { it -> it.authority })
            .sign(algorithm)


        // Response to user
        val tokensResponse =
            BaseResponse.success(
                mapOf<String, String>(
                    ACCESS_TOKEN to accessToken,
                    REFRESH_TOKEN to refreshToken
                )
            )
        response?.contentType = APPLICATION_JSON_VALUE

        ObjectMapper().writeValue(response?.outputStream, tokensResponse)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {

        throw failed!!
    }
}