package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.moneytorback.model.User
import spark.Request
import spark.Response
import javax.servlet.http.HttpServletResponse.SC_CREATED
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.validation.Validator

class UserController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
) : AbstractController(mapper, validator) {

    fun registerUser(request: Request, response: Response) {
        val user = parseRequestBody(request, User::class.java)
        // TODO call service to register user
        response.status(SC_CREATED)
    }

    fun login(request: Request, response: Response): User {
        val user = parseRequestBody(request, User::class.java)
        // TODO call service to register user
        response.status(SC_OK)
        return user
    }
}