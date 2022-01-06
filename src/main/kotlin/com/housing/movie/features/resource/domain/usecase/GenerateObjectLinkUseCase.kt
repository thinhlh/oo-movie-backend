package com.housing.movie.features.resource.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.resource.domain.service.ResourcesService
import org.springframework.stereotype.Component
import java.net.URL
import java.util.*

@Component
class GenerateObjectLinkUseCase(
    private val resourcesService: ResourcesService
) : BaseUseCase {
    override fun invoke(data: Any?): URL {
        return resourcesService.generateLink(data as String)
    }
}