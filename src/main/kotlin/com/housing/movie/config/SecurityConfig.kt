package com.housing.movie.config

import com.housing.movie.config.filter.CustomAuthenticationFilter
import com.housing.movie.config.filter.CustomAuthorizationFilter
import com.housing.movie.config.handlers.CustomAccessDeniedHandler
import com.housing.movie.utils.SecurityHelper
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


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

    /**
     * This function is used for authorization purpose by using user detail service,
     * which is implemented by UserService. this will grand authorities to the user
     * */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.cors().and()
            it.csrf().disable().httpBasic()
            it.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            addAuthorizationPaths(it)

            it.authorizeRequests().anyRequest().authenticated()
            it.exceptionHandling().accessDeniedHandler(CustomAccessDeniedHandler())
            it.addFilter(CustomAuthenticationFilter(authenticationManagerBean()))
            it.addFilterBefore(CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        }
    }

    private fun addAuthorizationPaths(http: HttpSecurity) {
        http.authorizeRequests().run {

            SecurityHelper.REQUEST_AUTHORIZATION_PATH.forEach { (pathAndMethods, authorities) ->
                if (authorities == null) {
                    // Permit all
                    if (pathAndMethods.second == null) antMatchers(pathAndMethods.first).permitAll()
                    else pathAndMethods.second!!.forEach { method ->
                        antMatchers(method, pathAndMethods.first).permitAll()
                    }
                } else {
                    if (pathAndMethods.second == null) {
                        antMatchers(pathAndMethods.first).hasAnyAuthority(*authorities.map { it.value }.toTypedArray())
                    } else {
                        pathAndMethods.second!!.forEach { method ->
                            antMatchers(method, pathAndMethods.first).hasAnyAuthority(*authorities.map { it.value }
                                .toTypedArray())
                        }
                    }
                }
            }
        }
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOriginPatterns = listOf("*")
//        configuration.allowedOrigins = listOf("https://oomovie-admin.vercel.app/", "https://oomovie.vercel.app/")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}