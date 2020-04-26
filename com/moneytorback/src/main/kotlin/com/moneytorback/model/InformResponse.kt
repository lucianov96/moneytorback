package com.moneytorback.model

import java.util.*

data class InformResponse(
    val group: String,
    val dateSince: Date,
    val dateUntil: Date,
    val cardNumber: Long,
    val amount: Long
)