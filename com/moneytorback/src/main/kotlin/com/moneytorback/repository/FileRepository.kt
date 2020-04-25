package com.moneytorback.repository

import java.io.InputStream
import java.io.OutputStream

interface FileRepository {
    fun saveFile(file: InputStream, filename: String, contentType: String = "application/octet-stream"): String
    fun getFile(filename: String): OutputStream?
}
