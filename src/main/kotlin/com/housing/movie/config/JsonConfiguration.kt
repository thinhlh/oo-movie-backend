package com.housing.movie.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.housing.movie.features.common.serializers.MovieBeanSerializerModifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfiguration {
    @Bean
    fun initSerializingConfiguration() {
        val mapper = ObjectMapper()
        val simpleModule = SimpleModule()

        simpleModule.setSerializerModifier(MovieBeanSerializerModifier())
        mapper.registerModule(simpleModule)
    }
}