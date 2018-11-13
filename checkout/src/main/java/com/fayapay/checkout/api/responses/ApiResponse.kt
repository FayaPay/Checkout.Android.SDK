package com.fayapay.checkout.api.responses

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser

internal class ApiResponse(val json: String, val success: Boolean) {
    val error: ErrorResponse?
    get() = Klaxon().parse<ErrorResponse>(json)

    private val jsonObject: JsonObject = Parser().parse(json) as JsonObject

    inline fun <reified T> getData(): T? {
        return Klaxon().parse<T>(json)
    }

    fun get(name: String) : Any? = jsonObject.get(name)
}