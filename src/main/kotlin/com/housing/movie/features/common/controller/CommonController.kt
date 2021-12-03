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
        return successResponse.body(BaseResponse.success("Succeed"))
    }
}