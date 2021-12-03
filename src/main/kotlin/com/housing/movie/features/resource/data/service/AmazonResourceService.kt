package com.housing.movie.features.resource.data.service

import com.amazonaws.AmazonServiceException
import com.amazonaws.HttpMethod
import com.amazonaws.SdkClientException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.housing.movie.exceptions.ConversionException
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ResourceHandlingException
import com.housing.movie.features.resource.domain.service.ResourcesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.time.Instant
import java.util.*

@Service
class AmazonResourceService : ResourcesService {

    companion object {
        const val CONVERSION_EXCEPTION = "Cannot perform type conversion on this file"
        const val FILE_CANNOT_EMPTY = "Uploaded file cannot be empty"
        const val RESOURCE_NOT_FOUND = "There is no resource attached to this path"
        const val LINK_EXPIRED_IN: Long = 1000 * 60 * 60
    }

    @Autowired
    private lateinit var s3Client: AmazonS3

    @Autowired
    private lateinit var env: Environment

    private fun convertMultipartToFile(multipartFile: MultipartFile): File {
        val file = File(multipartFile.originalFilename!!)
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(multipartFile.bytes)
        } catch (e: IOException) {
            throw ConversionException(CONVERSION_EXCEPTION)
        }

        return file
    }

    override fun uploadFile(multipartFile: MultipartFile): String {
        try {
            if (multipartFile.isEmpty) {
                throw ResourceHandlingException(FILE_CANNOT_EMPTY)
            }

            val file: File = convertMultipartToFile(multipartFile)
            val fileName = UUID.randomUUID().toString() + ".${file.extension}"

            s3Client.putObject(env.getProperty("aws.bucket-name"), fileName, file)

            file.delete()
            return fileName

        } catch (e: AmazonServiceException) {
            throw ResourceHandlingException(e.errorMessage)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun generateLink(path: String): URL {
        try {

            if (!checkObjectExists(path))
                throw NotFoundException(RESOURCE_NOT_FOUND)

            val url = s3Client.generatePresignedUrl(
                GeneratePresignedUrlRequest(
                    env.getProperty("aws.bucket-name"),
                    path
                ).also {
                    val expirationTime = Date()
                    expirationTime.time = Instant.now().toEpochMilli() + LINK_EXPIRED_IN

                    it.expiration = expirationTime
                    it.method = HttpMethod.GET

                })

            return url
        } catch (e: SdkClientException) {
            throw ResourceHandlingException(e.message)
        }
    }

    private fun checkObjectExists(path: String): Boolean {
        return s3Client.doesObjectExist(env.getProperty("aws.bucket-name"), path)
    }

}