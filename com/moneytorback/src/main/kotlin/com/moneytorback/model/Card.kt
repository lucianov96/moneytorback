package com.moneytorback.model

open class Card(
    val id: Long,
    val firstFourNumbers: Long,
    val lastFourNumbers: Long,
    val cardType: String,
    val mark: String
)