package com.moneytorback.validators

import com.moneytorback.enums.PaidMethod.EFECTIVO
import com.moneytorback.enums.PaidMethod.TARJETA_CREDITO
import com.moneytorback.enums.PaidMethod.TARJETA_DEBITO
import com.moneytorback.enums.PaidMethod.TRANSFERENCIA_BANCARIA
import com.moneytorback.model.Movement

class MovementValidator {
    fun validateMovement(movement: Movement): Boolean {
        return if (isExpense(movement.type)) {
            validateDescriptionMovement(movement.description) &&
            validatePaidMethodMovement(movement.paidMethod)
        } else if (isEntry(movement.type)) {
            validateDescriptionMovement(movement.description)
        } else {
            false
        }
    }

    private fun isExpense(type: String): Boolean {
        return type == "Expense"
    }

    private fun isEntry(type: String): Boolean {
        return type == "Entry"
    }

    private fun validateDescriptionMovement(description: String): Boolean {
        return description.length > 50
    }

    private fun validatePaidMethodMovement(paidMethod: String): Boolean {
        return paidMethod.toUpperCase() == EFECTIVO.abbreviate ||
                paidMethod.toUpperCase() == TARJETA_DEBITO.abbreviate ||
                paidMethod.toUpperCase() == TARJETA_CREDITO.abbreviate ||
                paidMethod.toUpperCase() == TRANSFERENCIA_BANCARIA.abbreviate
    }
}