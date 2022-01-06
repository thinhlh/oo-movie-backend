package com.housing.movie.config.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.base.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, ex: AccessDeniedException?) {
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.status = HttpStatus.FORBIDDEN.value()
        ObjectMapper().writeValue(response?.outputStream, BaseResponse.error(ex?.message.toString()))
    }
}