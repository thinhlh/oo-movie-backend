package com.housing.movie.config.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import com.housing.movie.config.SecurityConfig
import com.housing.movie.features.user.domain.entity.Role
import com.housing.movie.utils.SecurityHelper
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.Exception
import java.lang.NullPointerException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Authorization filter
 * */
@Component
class CustomAuthorizationFilter : OncePerRequestFilter() {

    private val ignoreTokenPaths = mutableMapOf<String, List<HttpMethod>?>()

    init {
        val pathsSetup: Map<Pair<String, List<HttpMethod>?>, List<Role>?> =
            SecurityHelper.REQUEST_AUTHORIZATION_PATH

        /**
         *  Retrieve all path that does not require token validation @param List<Role> = null
         *  The for each pathAndMethods, add the path with followed methods. if methods = null , this path is used for all method
         *  */

        pathsSetup.filter { pathSetup ->
            pathSetup.value == null
        }.keys.toList().onEach { pathAndMethods ->
            ignoreTokenPaths.putIfAbsent(pathAndMethods.first, pathAndMethods.second)
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if (filterIgnorePaths(request)) {
            filterChain.doFilter(request, response)
        } else {
            SecurityHelper.authenticate(request, response) { authentication ->
                // Tell the spring that this the the valid authentication
                SecurityContextHolder.getContext().authentication = authentication
                filterChain.doFilter(request, response)
            }
        }
    }

    private fun retrieveUsernameAndRoles(token: String?): Pair<String, List<String>> {
        val jwtVerifier: JWTVerifier = JWT.require(SecurityHelper.tokenHashAlgorithm()).build()
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

    // This path is not contained in ignored token paths
    private fun filterIgnorePaths(request: HttpServletRequest): Boolean {

        if (!ignoreTokenPaths.containsKey(request.servletPath)) return false

        val allowedMethods = ignoreTokenPaths[request.servletPath]
        if (allowedMethods == null) {
            // This allow all method on this path
            return true
        } else {
            // This allow some methods on this path
            return allowedMethods.contains(HttpMethod.valueOf(request.method))
        }
    }
}