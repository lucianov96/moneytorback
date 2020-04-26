package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import javax.validation.Validator
import com.google.inject.Inject
import com.moneytorback.model.Inform
import com.moneytorback.model.InformResponse
import com.moneytorback.services.InformService
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_OK

class InformController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator,
    private val informService: InformService
) : AbstractController(mapper, validator) {

    fun getInformFromRequest(request: Request, response: Response): List<InformResponse> {
        val userId = getUserIdFromRequest(request)
        val inform = parseRequestBody(request, Inform::class.java)
        response.status(SC_OK)
        return informService.getInform(userId, inform)
    }
}