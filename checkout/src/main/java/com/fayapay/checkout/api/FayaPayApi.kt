package com.fayapay.checkout.api

import com.beust.klaxon.Klaxon
import com.fayapay.checkout.api.data.ApiError
import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.data.TokenOwnerDetails
import com.fayapay.checkout.exceptions.FayaPayException
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import org.jetbrains.anko.coroutines.experimental.bg

internal class FayaPayApi : IFayaPayApi {
    private val Json = Klaxon()
    override fun initPhoneVerification(phoneNumber: String) {
    }

    override fun generateMobileMoneyToken(params: MomoTokenParams, tokenOwnerDetails: TokenOwnerDetails): String {
        return ""
    }

    override fun createToken() {
    }

    fun initialize(publishableKey: String) {
        FuelManager.instance.basePath = "https://public.fayapay.io/api/v1/checkout/"
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "X-PubKey" to publishableKey)

        bg {
            "Init".httpPost().response { _, response, result ->
                if (response.statusCode != 200) {
                    val error = Json.parse<ApiError>(result.component1().toString())!!
                    throw FayaPayException(error.messages.joinToString { it + "\n" })
                }
            }
        }
    }
}