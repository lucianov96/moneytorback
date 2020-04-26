package com.moneytorback.model

import java.util.*

open class Report(
    val description: String,
    val group: String,
    val paidMethod: String,
    val amountCondition: String,
    val amount: Long,
    val dateCondition: String,
    val date: Date
)