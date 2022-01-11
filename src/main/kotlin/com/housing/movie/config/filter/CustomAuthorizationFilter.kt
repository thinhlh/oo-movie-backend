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

        pathsSetup
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        SecurityHelper.authenticate(request, response) { authResult ->
            // Tell the spring that this the the valid authentication
            SecurityContextHolder.getContext().authentication = authResult
            filterChain.doFilter(request, response)
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return filterIgnorePaths(request)
    }

    // This path is not contained in ignored token paths
    private fun filterIgnorePaths(request: HttpServletRequest): Boolean {

        if (!ignoreTokenPaths.any {
                // This is for all followed pattern
                if (it.key.endsWith("**"))
                    it.key.startsWith(request.servletPath)
                else
                    it.key == request.servletPath
            }) return false

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