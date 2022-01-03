package com.housing.movie.config.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.SecurityConfig
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.Exception
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Authorization filter
 * */
@Component
class CustomAuthorizationFilter : OncePerRequestFilter() {

    private val algorithm: Algorithm = SecurityConfig.tokenHashAlgorithm()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Pass to the next filter
        if (request.servletPath == "/login") {
            filterChain.doFilter(request, response)
        } else {

            try {
                /**
                 * We first retrieve the token, the achieve the username and roles which is hashed by the token
                 * Then we will tell the sping context that the given username has a role like specified in authorities
                 * */
                val token = getTokenFromHeader(request)

                val usernameAndRoles = retrieveUsernameAndRoles(token)
                val username = usernameAndRoles.first

                val authorities: List<SimpleGrantedAuthority> =
                    usernameAndRoles.second.map { SimpleGrantedAuthority(it) }
                val usernamePasswordAuthentication = UsernamePasswordAuthenticationToken(username, null, authorities)

                // Tell the spring that this the the valid authentication
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthentication
                filterChain.doFilter(request, response)
            } catch (e: Exception) {
                val errorResponse = BaseResponse.error(e.message.toString())
                response.contentType = APPLICATION_JSON_VALUE
                response.status = HttpStatus.UNAUTHORIZED.value()
                ObjectMapper().writeValue(response.outputStream, errorResponse)
            }

        }
    }

    private fun retrieveUsernameAndRoles(token: String?): Pair<String, List<String>> {
        val jwtVerifier: JWTVerifier = JWT.require(algorithm).build()
        val decodedJWT: DecodedJWT = jwtVerifier.verify(token)

        val username = decodedJWT.subject

        // Actually in this application, we only use one role for each user
        val roles = decodedJWT.getClaim("roles").asList(String::class.java)

        return Pair(username, roles)
    }

    private fun getTokenFromHeader(request: HttpServletRequest): String? {
        val authorizationHeader: String? = request.getHeader(AUTHORIZATION)

        return if (authorizationHeader == null) null
        else if (!authorizationHeader.startsWith("Bearer ")) null
        else authorizationHeader.substring("Bearer ".length)
    }
}