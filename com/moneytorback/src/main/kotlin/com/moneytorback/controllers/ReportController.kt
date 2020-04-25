package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import javax.validation.Validator

class ReportController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
): AbstractController(mapper, validator) {
}