package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.Expense
import com.moneytorback.services.ExpenseService
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_CREATED
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class ExpenseController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator,
    private val expenseService: ExpenseService
) : AbstractController(mapper, validator) {

    fun getUserExpenses(request: Request, response: Response): List<Expense> {
        val userId = getUserIdFromRequest(request)
        response.status(SC_OK)
        return expenseService.getUserExpenses(userId)
    }

    fun registerExpense(request: Request, response: Response) {
        val expense = parseRequestBody(request, Expense::class.java)
        expenseService.registerExpense(expense)
        response.status(SC_CREATED)
    }
}