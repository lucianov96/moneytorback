package com.moneytorback.model

import java.util.*

data class InformResponse(
    val group: Group,
    val dateSince: Date,
    val dateUntil: Date,
    val cardNumber: Long,
    val amount: Long
)