package com.housing.movie.base

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

abstract class BaseController {
    protected val logger: Logger = LoggerFactory.getLogger(this::class.java)

    protected fun <T> successResponse(body: T): ResponseEntity<BaseResponse<T>> {
        return ResponseEntity.ok(BaseResponse.success(body))
    }

}