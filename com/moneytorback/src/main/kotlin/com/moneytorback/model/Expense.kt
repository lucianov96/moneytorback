package com.moneytorback.model

open class Expense(
    val id: Long,
    val description: String,
    val group: String,
    val paidMethod: String,
    val amount: Long,
    val installments: Long
)