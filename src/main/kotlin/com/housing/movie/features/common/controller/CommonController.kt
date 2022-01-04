package com.housing.movie.features.common.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Common", description = "Common APIs")
class CommonController : BaseController() {


    @GetMapping("/ping")
    @ApiResponse(responseCode = "200", description = "Succeed")
    fun ping(): ResponseEntity<BaseResponse<String>> {
        return successResponse("Succeed")
    }

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Succeed")
    fun info(): ResponseEntity<BaseResponse<String>> {
        return successResponse("Housing Movie Application")
    }

    @GetMapping("/ping/admin")
    fun pingAdmin(): ResponseEntity<BaseResponse<String>> {
        return successResponse("You are admin")
    }

    @GetMapping("/ping/subscriber")
    fun pingSubscriber(): ResponseEntity<BaseResponse<String>> {
        return successResponse("You are subscriber")
    }
}