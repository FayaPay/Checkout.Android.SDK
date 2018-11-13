package com.fayapay.checkout.api.responses

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser

internal class ApiResponse(val json: String, val success: Boolean) {
    val error: ErrorResponse?
        get() = Klaxon().parse<ErrorResponse>(json)

    private var jsonObject: JsonObject

    init {
        try {
            jsonObject = Parser().parse(StringBuilder(json)) as JsonObject
        } catch (e: Exception) {
            jsonObject = JsonObject()
        }
    }

    inline fun <reified T> getData(): T? {
        return Klaxon().parse<T>(json)
    }

    fun <T> get(name: String): T? = jsonObject.get(name) as T
}