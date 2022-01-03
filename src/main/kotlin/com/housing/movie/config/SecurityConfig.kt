package com.housing.movie.config

import com.auth0.jwt.algorithms.Algorithm
import com.housing.movie.config.filter.CustomAuthenticationFilter
import com.housing.movie.config.filter.CustomAuthorizationFilter
import com.housing.movie.features.user.domain.entity.Role
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Configuration for security
 * */

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val bCryptPasswordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    companion object {
        val REQUEST_AUTHORIZATION = mapOf<Map<HttpMethod?, String>, List<Role>?>(
            mapOf<HttpMethod?, String>(
                null to "/login"
            ) to null,
            mapOf<HttpMethod?, String>(
                HttpMethod.GET to "/ping"
            ) to listOf(Role.Subscriber, Role.Administrator)
        )

        fun tokenHashAlgorithm(): Algorithm = Algorithm.HMAC256("housingmovie")
    }

    /**
     * This function is used for authorization purpose by using user detail service,
     * which is implemented by UserService. this will grand authorities to the user
     * */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.csrf().disable()
            it.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            addAuthorizationPaths(it)

            it.authorizeRequests().anyRequest().authenticated()

            it.addFilter(CustomAuthenticationFilter(authenticationManagerBean()))
            it.addFilterBefore(CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        }
    }

    private fun addAuthorizationPaths(http: HttpSecurity) {
        http.authorizeRequests().run {
            REQUEST_AUTHORIZATION.forEach { (methodAndPath, authorities) ->
                methodAndPath.forEach { (method, path) ->
                    if (authorities == null) {
                        // Permit all
                        if (method == null) antMatchers(path).permitAll()
                        else antMatchers(method, path).permitAll()
                    } else {
                        if (method == null) antMatchers(path).hasAnyAuthority(*authorities.map { it.value }
                            .toTypedArray())
                        else antMatchers(method, path).hasAnyAuthority(*authorities.map { it.value }.toTypedArray())
                    }
                }
            }
        }
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}