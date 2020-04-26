package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import javax.validation.Validator
import com.google.inject.Inject
import com.moneytorback.model.Inform
import com.moneytorback.model.InformResponse
import spark.Request
import spark.Response

class InformController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
) : AbstractController(mapper, validator) {

    fun getInformFromRequest(request: Request, response: Response): List<InformResponse> {
        val inform = parseRequestBody(request, Inform::class.java)
        // TODO call service to get inform
        return listOf()
    }
}