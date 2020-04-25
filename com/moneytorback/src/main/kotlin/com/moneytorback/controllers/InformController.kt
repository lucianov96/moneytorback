package com.moneytorback.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import javax.validation.Validator
import com.google.inject.Inject

class InformController @Inject constructor(
    private val mapper: ObjectMapper,
    private val validator: Validator
): AbstractController(mapper, validator) {
}