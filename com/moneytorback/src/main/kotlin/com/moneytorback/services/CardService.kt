package com.moneytorback.services

import com.google.inject.Inject
import com.moneytorback.exceptions.BadRequestException
import com.moneytorback.model.Card
import com.moneytorback.validators.CardValidator

class CardService @Inject constructor(
    private val cardValidator: CardValidator
) {
    fun getCardsByUserId(userId: Long): List<Card> {
        // TODO get cards firebase
        return listOf()
    }

    fun registerCard(card: Card) {
        if (!cardValidator.validateCard(card)) {
            throw BadRequestException("Card with numbers ${card.firstFourNumbers}-${card.lastFourNumbers} has invalid data")
        }
        // TODO register card firebase
    }
}