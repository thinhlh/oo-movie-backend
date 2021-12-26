package com.housing.movie.features.resource.domain.service

import com.housing.movie.exceptions.ConversionException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.util.*

interface ResourcesService {

    companion object {
        const val CONVERSION_EXCEPTION = "Cannot perform type conversion on this file"
        const val FILE_CANNOT_EMPTY = "Uploaded file cannot be empty"
        const val RESOURCE_NOT_FOUND = "There is no resource attached to this path"
        const val LINK_EXPIRED_IN: Long = 1000 * 60 * 60
    }

    fun uploadFile(multipartFile: MultipartFile): String

    fun generateLink(path: String): URL

    fun convertMultipartToFile(multipartFile: MultipartFile): File {
        val file = File(multipartFile.originalFilename!!)
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(multipartFile.bytes)
        } catch (e: IOException) {
            throw ConversionException(CONVERSION_EXCEPTION)
        }

        return file
    }
}