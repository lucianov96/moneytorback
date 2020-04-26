package com.moneytorback.services

import com.google.inject.Inject
import com.moneytorback.exceptions.BadRequestException
import com.moneytorback.model.Expense
import com.moneytorback.model.Report
import com.moneytorback.validators.ExpenseValidator
import com.moneytorback.validators.ReportValidator

class ExpenseService @Inject constructor(
    private val expenseValidator: ExpenseValidator,
    private val reportValidator: ReportValidator
) {
    fun getUserExpenses(userId: Long): List<Expense> {
        // TODO call to firebase service to return user expenses list
        return listOf()
    }

    fun registerExpense(expense: Expense) {
        if (!expenseValidator.validateExpense(expense)) {
            throw BadRequestException("Expense $expense has invalid data")
        }
        // TODO call to firebase service to register expense
    }

    fun getDesiredExpenses(userId: Long, report: Report): List<Expense> {
        if (!reportValidator.validateReport(report)) {
            throw BadRequestException("Expense $report has invalid data")
        }
        // TODO call to firebase service to get expenses
        return listOf()
    }
}