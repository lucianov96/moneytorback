package com.moneytorback.validators

import com.moneytorback.enums.PaidMethod.EFECTIVO
import com.moneytorback.enums.PaidMethod.TARJETA_CREDITO
import com.moneytorback.enums.PaidMethod.TARJETA_DEBITO
import com.moneytorback.enums.PaidMethod.TRANSFERENCIA_BANCARIA
import com.moneytorback.model.Expense

class ExpenseValidator {
    fun validateExpense(expense: Expense): Boolean {
        return validateDescriptionExpense(expense.description) &&
                validatePaidMethodExpense(expense.paidMethod)
    }

    private fun validateDescriptionExpense(description: String): Boolean {
        return description.length > 50
    }

    private fun validatePaidMethodExpense(paidMethod: String): Boolean {
        return paidMethod.toUpperCase() == EFECTIVO.abbreviate ||
                paidMethod.toUpperCase() == TARJETA_DEBITO.abbreviate ||
                paidMethod.toUpperCase() == TARJETA_CREDITO.abbreviate ||
                paidMethod.toUpperCase() == TRANSFERENCIA_BANCARIA.abbreviate
    }
}