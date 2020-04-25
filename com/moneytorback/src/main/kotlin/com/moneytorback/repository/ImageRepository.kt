package com.moneytorback.repository

import com.google.cloud.storage.Acl
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.Bucket
import com.moneytorback.providers.BucketRepositoryProvider
import java.io.InputStream
import java.io.OutputStream

class ImageRepository : FileRepository {

    companion object {
        private const val STATIC_IMAGE_URL: String = "https://storage.googleapis.com/agroempresario-bucket/"
    }

    private val bucket: Bucket = BucketRepositoryProvider().get()
    private val folder: String = "images/"

    override fun getFile(filename: String): OutputStream? {
        return null
    }

    override fun saveFile(file: InputStream, filename: String, contentType: String): String {
        val blob = bucket.create("$folder$filename", file, contentType)
        val blobId = BlobId.of(blob.bucket, blob.name, blob.generation)

        // Set the image public.
        BucketRepositoryProvider().storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))

        return STATIC_IMAGE_URL + blob.name
    }
}
