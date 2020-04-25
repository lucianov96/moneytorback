package com.moneytorback.providers

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.StorageOptions
import java.io.InputStream

@Suppress("UNUSED")
class BucketRepositoryProvider {
    val serviceAccount: InputStream = this::class.java.classLoader.getResourceAsStream("agroempresario-digital-58b887604308.json")

    val storage = StorageOptions.newBuilder()
        .setCredentials(GoogleCredentials.fromStream(
            serviceAccount
        ))
        .build().service

    fun get(): Bucket {
        // The name for the new bucket
        val bucketName = "agroempresario-bucket"

        // Creates the new bucket
        return storage.get(bucketName)
    }
}
