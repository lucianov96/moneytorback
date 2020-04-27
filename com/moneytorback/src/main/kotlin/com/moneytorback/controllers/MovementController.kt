package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.Movement
import com.moneytorback.services.MovementService
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_CREATED
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class MovementController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator,
    private val movementService: MovementService
) : AbstractController(mapper, validator) {

    fun getUserMovements(request: Request, response: Response): List<Movement> {
        val userId = getUserIdFromRequest(request)
        val type = getTypeFromRequest(request)
        response.status(SC_OK)
        return movementService.getUserMovements(userId, type)
    }

    fun registerMovement(request: Request, response: Response) {
        val movement = parseRequestBody(request, Movement::class.java)
        movementService.registerMovement(movement)
        response.status(SC_CREATED)
    }

    private fun getTypeFromRequest(request: Request): String {
        return request.params().getOrDefault(":type", "").toString()
    }
}