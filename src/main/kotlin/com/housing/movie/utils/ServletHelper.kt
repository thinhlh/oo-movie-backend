package com.housing.movie.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.DecodedJWT
import com.housing.movie.config.filter.CustomAuthenticationFilter
import com.housing.movie.exceptions.UnknownException
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.lang.Exception
import java.lang.IllegalStateException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object ServletHelper {

    /**
     * Retrieve current request, throw Unknown error if unable identify request
     * */
    fun request(): HttpServletRequest {
        try {
            return (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        } catch (e: IllegalStateException) {
            throw UnknownException(e.message)
        }
    }

    /**
     * Retrieve current request, throw @exception UnknownException if untable to identify response
     * */
    fun response(): HttpServletResponse {
        try {
            return (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).response!!
        } catch (e: Exception) {
            throw UnknownError(e.message.toString())
        }
    }

    /**
     * Retrieve username and roles from current sent token
     * */
    fun retrieveUsernameAndRoles(
        authorizationToken: String? = null,
        invalidTokenCallback: (() -> Unit)? = null,
    ): Pair<String, List<String>> {

        // If token is not null, this is the authentication case (where request is not yet mapped)
        val token: String? = if (authorizationToken != null) {
            decodeToken(authorizationToken)
        } else {
            getRawAuthorizationJWTToken()
        }

        if (token == null) {
            invalidTokenCallback?.invoke()
            return ("" to listOf())
        }

        val jwtVerifier: JWTVerifier = JWT.require(SecurityHelper.tokenHashAlgorithm()).build()
        val decodedJWT: DecodedJWT = jwtVerifier.verify(token)

        val username = decodedJWT.subject

        // Actually in this application, we only use one role for each user
        val roles = decodedJWT.getClaim(CustomAuthenticationFilter.claim).asList(String::class.java)

        return Pair(username, roles)
    }

    /*
    * Get raw JWT token without bearer
    * */
    fun getRawAuthorizationJWTToken(): String? =
        decodeToken(request().getHeader(AUTHORIZATION))

    // Decode token from raw token to extracted token
    private fun decodeToken(token: String?): String? {
        return if (token == null || token.isNullOrBlank()) null
        else if (!token.startsWith("Bearer ")) null
        else token.substring("Bearer ".length)
    }
}