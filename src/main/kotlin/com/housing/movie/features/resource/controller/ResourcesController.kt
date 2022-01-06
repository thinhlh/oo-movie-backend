package com.housing.movie.features.resource.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.resource.domain.usecase.GenerateObjectLinkUseCase
import com.housing.movie.features.resource.domain.usecase.UploadVideoUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URL

@RestController
@Tag( name = "Resources")
class ResourcesController(
    private val uploadVideoUseCase: UploadVideoUseCase,
    private val generateObjectLinkUseCase: GenerateObjectLinkUseCase,
) : BaseController() {

    @PostMapping("/upload", consumes = ["multipart/form-data"])
    fun uploadVideo(file: MultipartFile): ResponseEntity<BaseResponse<String>> {
        return successResponse(uploadVideoUseCase(file))
    }

    @GetMapping("/download")
    fun generateDownloadVideo(@RequestParam path: String): ResponseEntity<BaseResponse<URL>> {
        return successResponse(generateObjectLinkUseCase(path))
    }

}