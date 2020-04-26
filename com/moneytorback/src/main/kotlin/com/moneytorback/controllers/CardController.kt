package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.Card
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_CREATED
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class CardController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
) : AbstractController(mapper, validator) {

    fun registerCard(request: Request, response: Response) {
        val card = parseRequestBody(request, Card::class.java)
        // TODO call service to register card
        response.status(SC_CREATED)
    }

    fun getCards(request: Request, response: Response): List<Card> {
        val userId = getUserIdFromRequest(request)
        // TODO call service to get user cards
        response.status(SC_OK)
        return listOf()
    }
}