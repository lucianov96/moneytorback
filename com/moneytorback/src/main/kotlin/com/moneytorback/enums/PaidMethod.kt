package com.moneytorback.enums

enum class PaidMethod(val abbreviate: String) {
    EFECTIVO("EF"),
    TARJETA_DEBITO("TD"),
    TARJETA_CREDITO("TC"),
    TRANSFERENCIA_BANCARIA("TB")
}