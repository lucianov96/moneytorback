package com.moneytorback.model

open class Movement(
    val id: Long,
    val description: String,
    val group: Group,
    val paidMethod: String,
    val amount: Long,
    val installments: Long,
    val type: String
)