package com.moneytorback.services

import com.google.inject.Inject
import com.moneytorback.exceptions.BadRequestException
import com.moneytorback.model.Inform
import com.moneytorback.model.InformResponse
import com.moneytorback.validators.InformValidator

class InformService @Inject constructor(
    private val informValidator: InformValidator
) {
    fun getInform(userId: Long, inform: Inform): List<InformResponse> {
        if (!informValidator.validateInform(inform)) {
            throw BadRequestException("Inform $inform has invalid data")
        }
        // TODO call service in firebase to get the inform
        return listOf()
    }
}