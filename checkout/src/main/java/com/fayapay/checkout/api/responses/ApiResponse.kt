package com.fayapay.checkout.api.responses

import com.beust.klaxon.Klaxon

internal class ApiResponse(val json: String, val success: Boolean) {
    val error: ErrorResponse?
    get() = Klaxon().parse<ErrorResponse>(json)

    inline fun <reified T> getData(): T? {
        return Klaxon().parse<T>(json)
    }
}