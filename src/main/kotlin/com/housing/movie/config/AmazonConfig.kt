package com.housing.movie.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment


@Configuration
class AmazonConfig {
    @Autowired
    private lateinit var env: Environment

    @Bean
    fun amazonS3(): AmazonS3 {
        val awsCredentials: AWSCredentials =
            BasicAWSCredentials(env.getProperty("aws.access-key"), env.getProperty("aws.secret-access-key"))

        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.AP_SOUTHEAST_1)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}