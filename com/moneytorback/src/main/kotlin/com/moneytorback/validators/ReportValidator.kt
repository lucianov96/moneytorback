package com.moneytorback.validators

import com.moneytorback.enums.PaidMethod
import com.moneytorback.enums.SearchCondition.EQUAL
import com.moneytorback.enums.SearchCondition.LESS
import com.moneytorback.enums.SearchCondition.MESS
import com.moneytorback.model.Report

class ReportValidator {
    fun validateReport(report: Report): Boolean {
        return validateReportDescription(report.description) &&
                validateReportPaidMethod(report.paidMethod) &&
                validateCondition(report.amountCondition) &&
                validateCondition(report.dateCondition)
    }

    private fun validateReportDescription(description: String): Boolean {
        return description.length <= 50
    }

    private fun validateReportPaidMethod(paidMethod: String): Boolean {
        return paidMethod.toUpperCase() == PaidMethod.EFECTIVO.abbreviate ||
                paidMethod.toUpperCase() == PaidMethod.TARJETA_DEBITO.abbreviate ||
                paidMethod.toUpperCase() == PaidMethod.TARJETA_CREDITO.abbreviate ||
                paidMethod.toUpperCase() == PaidMethod.TRANSFERENCIA_BANCARIA.abbreviate
    }

    private fun validateCondition(condition: String): Boolean {
        return condition.toUpperCase() == EQUAL.name ||
                condition.toUpperCase() == LESS.name ||
                condition.toUpperCase() == MESS.name
    }
}