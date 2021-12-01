package com.housing.movie.base

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

abstract class BaseController {
    protected val logger: Logger = LoggerFactory.getLogger(this::class.java)

    protected val successResponse: ResponseEntity.BodyBuilder =
            ResponseEntity
                    .ok()

}