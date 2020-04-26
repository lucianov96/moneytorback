package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.Expense
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_CREATED
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class ExpenseController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
) : AbstractController(mapper, validator) {

    fun getUserExpenses(request: Request, response: Response): List<Expense> {
        val userId = getUserIdFromRequest(request)
        // TODO call service to get user expenses
        response.status(SC_OK)
        return listOf()
    }

    fun registerExpense(request: Request, response: Response) {
        val expense = parseRequestBody(request, Expense::class.java)
        // TODO call service to register user
        response.status(SC_CREATED)
    }
}