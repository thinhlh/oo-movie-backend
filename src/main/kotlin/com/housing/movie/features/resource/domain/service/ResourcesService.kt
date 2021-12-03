package com.housing.movie.features.resource.domain.service

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.URL
import java.util.*

interface ResourcesService {
    fun uploadFile(multipartFile: MultipartFile): String

    fun generateLink(path: String): URL
}