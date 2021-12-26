package com.housing.movie.features.resource.data.service

import com.amazonaws.AmazonServiceException
import com.google.cloud.storage.*
import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.StorageClient
import com.housing.movie.exceptions.ResourceHandlingException
import com.housing.movie.features.resource.domain.service.ResourcesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit


@Service
class FirebaseResourceService : ResourcesService {
    @Autowired
    private lateinit var env: Environment

    override fun uploadFile(multipartFile: MultipartFile): String {
        try {
            if (multipartFile.isEmpty) {
                throw ResourceHandlingException(ResourcesService.FILE_CANNOT_EMPTY)
            }

            val file: File = convertMultipartToFile(multipartFile)
            val fileName = UUID.randomUUID().toString() + ".${file.extension}"

            StorageClient.getInstance().bucket().create(fileName, file.readBytes(), multipartFile.contentType)

            return fileName

        } catch (e: AmazonServiceException) {
            throw ResourceHandlingException(e.errorMessage)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun generateLink(path: String): URL {
        val storage = StorageClient.getInstance().bucket().storage

        val blobInfo = BlobInfo.newBuilder(BlobId.of("housing-movie.appspot.com", path)).build()
        return storage.signUrl(blobInfo, 3, TimeUnit.HOURS, Storage.SignUrlOption.withV4Signature())
    }
}