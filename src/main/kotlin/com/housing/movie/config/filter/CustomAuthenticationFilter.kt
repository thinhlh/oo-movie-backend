package com.housing.movie.config.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.SecurityConfig
import com.housing.movie.exceptions.CustomAuthenticationException
import com.housing.movie.features.auth.domain.entity.LoginRequest
import com.housing.movie.features.auth.domain.entity.Tokens
import com.housing.movie.utils.SecurityHelper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
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
        const val ACCESS_TOKEN_TIMEOUT = 1 * 60 * 1000 // 1 hour
        const val REFRESH_TOKEN_TIMEOUT = 7 * 24 * 60 * 60 * 1000 // 1 week
    }

    private val algorithm: Algorithm = SecurityHelper.tokenHashAlgorithm()

    // This is called whenever user call in the /login api
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val authRequest = getLoginRequest(request)

        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken)
    }

    private fun getLoginRequest(request: HttpServletRequest?): LoginRequest {

        request ?: throw CustomAuthenticationException(message = "Unable to authenticate user.")

        val sb = StringBuilder()
        val reader = request.reader
        reader.use { it ->
            var line: String?
            while (it.readLine().also { line = it } != null) {
                sb.append(line).append(' ')
            }
        }
        val jsonString = sb.toString()

        return ObjectMapper().readValue(jsonString, LoginRequest::class.java)
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
        val accessToken = SecurityHelper.generateToken(
            username = user?.username,
            timeout = ACCESS_TOKEN_TIMEOUT,
            authorities = user?.authorities?.map { it -> it.authority },
            requestUrl = request?.requestURL.toString()
        )

        // One day expire
        val refreshToken = SecurityHelper.generateToken(
            username = user?.username,
            timeout = REFRESH_TOKEN_TIMEOUT,
            authorities = user?.authorities?.map { it -> it.authority },
            requestUrl = request?.requestURL.toString()
        )


        // Response to user
        val tokensResponse =
            BaseResponse.success(Tokens(accessToken, refreshToken))
        response?.contentType = APPLICATION_JSON_VALUE

        ObjectMapper().writeValue(response?.outputStream, tokensResponse)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        response?.contentType = APPLICATION_JSON_VALUE
        response?.status = HttpStatus.FORBIDDEN.value()
        ObjectMapper().writeValue(response?.outputStream, BaseResponse.error(failed?.message.toString()))

    }
}