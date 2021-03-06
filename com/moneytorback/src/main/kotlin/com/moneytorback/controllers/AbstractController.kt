package com.moneytorback.controllers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.moneytorback.exceptions.ApiException
import spark.Request
import javax.validation.Validator

open class AbstractController(
    private val mapper: ObjectMapper,
    private val validator: Validator
) {
    protected fun <T> parseRequestBody(request: Request, desiredClass: Class<T>): T {
        val parsed = try {
            mapper.readValue(request.body(), desiredClass)
        } catch (e: MissingKotlinParameterException) {
            throw ApiException("key [${getExceptionParameter(e)}] not found", 500, e, null)
        } catch (e: MismatchedInputException) {
            throw ApiException("type mismatch for key [${getExceptionParameter(e)}]", 500, e, null)
        } catch (e: JsonProcessingException) {
            throw ApiException("error in parsing body -> ${e.message}", 500, e, null)
        }

        val errors = validator.validate(parsed)

        if (errors.isNotEmpty()) {
            val message = errors.joinToString(prefix = "[", postfix = "]") { "\"${it.message}\"" }
            throw ApiException(message, 500, null, null)
        }
        return parsed
    }

    fun getUserIdFromRequest(request: Request): Long {
        return request.params().getOrDefault(":id", "0").toLong()
    }

    private fun getExceptionParameter(e: JsonMappingException): String =
        e.path.joinToString(".") {
            if (it.index >= 0)
                "[${it.index}]"
            else it.fieldName
        }
}