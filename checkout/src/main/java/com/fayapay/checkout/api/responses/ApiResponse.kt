package com.fayapay.checkout.api.responses

import com.beust.klaxon.Klaxon

internal class ApiResponse(val json: String, val success: Boolean) {
    inline fun <reified T> getData(): T? {
        return Klaxon().parse<T>(json)
    }
}