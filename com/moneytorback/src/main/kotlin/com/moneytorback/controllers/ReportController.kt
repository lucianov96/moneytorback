package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.Movement
import com.moneytorback.model.Report
import com.moneytorback.services.MovementService
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class ReportController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator,
    private val movementService: MovementService
) : AbstractController(mapper, validator) {

    fun getMovementsWithReport(request: Request, response: Response): List<Movement> {
        val userId = getUserIdFromRequest(request)
        val report = parseRequestBody(request, Report::class.java)
        response.status(SC_OK)
        return movementService.getDesiredMovements(userId, report)
    }
}