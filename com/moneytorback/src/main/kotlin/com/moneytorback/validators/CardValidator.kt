package com.moneytorback.validators

import com.moneytorback.enums.CardMark.VISA
import com.moneytorback.enums.CardMark.AMERICAN_EXPRESS
import com.moneytorback.enums.CardMark.MASTER_CARD
import com.moneytorback.enums.CardType.DEBITO
import com.moneytorback.enums.CardType.CREDITO
import com.moneytorback.model.Card

class CardValidator {

    fun validateCard(card: Card): Boolean {
        return validateCardNumbers(card.firstFourNumbers, card.lastFourNumbers) &&
            validateCardMark(card.mark) &&
            validateCardType(card.cardType)
    }

    private fun validateCardNumbers(firstFourNumbers: Long, lastFourNumbers: Long): Boolean {
        return !(firstFourNumbers<1000||
                firstFourNumbers>9999||
                lastFourNumbers<1000||
                lastFourNumbers>9999)
    }

    private fun validateCardMark(cardMark: String): Boolean {
        return cardMark.toUpperCase() == VISA.name ||
                cardMark.toUpperCase() == MASTER_CARD.name ||
                cardMark.toUpperCase() == AMERICAN_EXPRESS.name
    }

    private fun validateCardType(cardType: String): Boolean {
        return cardType.toUpperCase() == DEBITO.name ||
                cardType.toUpperCase() == CREDITO.name
    }
}