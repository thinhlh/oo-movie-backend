package com.housing.movie.config

/**
 * This configuration file is used for cors exception
 * */
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedMethods(
                "HEAD",
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH",
                "OPTIONS"
            )
            .allowedOrigins("https://oomovie-admin.vercel.app/**")
            .allowedHeaders("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization")
            .allowCredentials(true)
    }
}