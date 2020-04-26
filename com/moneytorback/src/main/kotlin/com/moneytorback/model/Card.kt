package com.moneytorback.model

open class Card(
    val userId: Long,
    val firstFourNumbers: Long,
    val lastFourNumbers: Long,
    val cardType: String,
    val mark: String
)