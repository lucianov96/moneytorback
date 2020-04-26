package com.moneytorback.validators

import com.moneytorback.enums.PaidMethod
import com.moneytorback.model.Inform
import java.util.*

class InformValidator {
    fun validateInform(inform: Inform): Boolean {
        return validatePaidMethodInform(inform.paidMethod) &&
            validateEasyOptions(inform.easyOption) &&
            validateDates(inform.sinceDate, inform.untilDate)
    }

    private fun validatePaidMethodInform(paidMethod: String): Boolean {
        return paidMethod.toUpperCase() == PaidMethod.EFECTIVO.abbreviate ||
                paidMethod.toUpperCase() == PaidMethod.TARJETA_DEBITO.abbreviate ||
                paidMethod.toUpperCase() == PaidMethod.TARJETA_CREDITO.abbreviate ||
                paidMethod.toUpperCase() == PaidMethod.TRANSFERENCIA_BANCARIA.abbreviate
    }

    private fun validateEasyOptions(easyOptions: String): Boolean {
        return easyOptions == "LW" || easyOptions == "LQ" || easyOptions == "LM" || easyOptions.isEmpty()
    }

    private fun validateDates(sinceDate: Date, untilDate: Date): Boolean {
        return sinceDate.compareTo(untilDate) == -1
    }
}