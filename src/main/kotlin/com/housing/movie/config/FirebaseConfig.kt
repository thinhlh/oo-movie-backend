package com.housing.movie.config


/**
 * Configuration for google cloud bucket
 * */
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.StorageClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.io.FileInputStream

@Configuration
class FirebaseConfig {
    @Autowired
    private lateinit var env: Environment

    @Bean
    fun bucket(): Storage {
        val serviceAccount =
            FileInputStream("src/main/resources/housing-movie-firebase-adminsdk-fzmgo-9417fad6c5.json")

        val options = FirebaseOptions
            .builder()
            .setProjectId("housing-movie")
            .setStorageBucket("housing-movie.appspot.com")
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)

        return StorageOptions.newBuilder().setProjectId("housing-movie").build().service
    }
}