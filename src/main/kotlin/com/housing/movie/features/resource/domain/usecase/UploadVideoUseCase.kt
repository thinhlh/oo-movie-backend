package com.housing.movie.features.resource.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.resource.domain.service.ResourcesService
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class UploadVideoUseCase(
    private val resourcesService: ResourcesService
) : BaseUseCase {
    override fun invoke(data: Any?): String {
        return resourcesService.uploadFile(data as MultipartFile)
    }
}