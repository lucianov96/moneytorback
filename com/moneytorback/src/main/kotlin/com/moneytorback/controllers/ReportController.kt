package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.Expense
import com.moneytorback.model.Report
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class ReportController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
) : AbstractController(mapper, validator) {

    fun getExpensesWithReport(request: Request, response: Response): List<Expense> {
        val report = parseRequestBody(request, Report::class.java)
        // TODO call expense service to get expenses
        response.status(SC_OK)
        return listOf()
    }
}