package com.moneytorback.services

import com.google.inject.Inject
import com.moneytorback.exceptions.BadRequestException
import com.moneytorback.model.Movement
import com.moneytorback.model.Report
import com.moneytorback.validators.MovementValidator
import com.moneytorback.validators.ReportValidator

class MovementService @Inject constructor(
    private val movementValidator: MovementValidator,
    private val reportValidator: ReportValidator
) {
    fun getUserMovements(userId: Long, type: String): List<Movement> {
        // TODO call to firebase service to return user movements list
        return listOf()
    }

    fun registerMovement(movement: Movement) {
        if (!movementValidator.validateMovement(movement)) {
            throw BadRequestException("Movement $movement has invalid data")
        }
        // TODO call to firebase service to register movements
    }

    fun getDesiredMovements(userId: Long, report: Report): List<Movement> {
        if (!reportValidator.validateReport(report)) {
            throw BadRequestException("Report $report has invalid data")
        }
        // TODO call to firebase service to get movements
        return listOf()
    }
}