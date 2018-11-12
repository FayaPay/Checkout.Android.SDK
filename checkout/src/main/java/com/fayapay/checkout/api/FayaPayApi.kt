package com.fayapay.checkout.api

import com.beust.klaxon.Klaxon
import com.fayapay.checkout.api.data.ApiMessage
import com.fayapay.checkout.api.data.ApiResponse
import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.data.TokenOwnerDetails
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import org.jetbrains.anko.coroutines.experimental.bg

internal class FayaPayApi {
    companion object {
        private val Json = Klaxon()

        suspend fun initialize(publishableKey: String): Boolean {
            FuelManager.instance.basePath = "https://public.fayapay.io/api/v1/checkout/"
            FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "X-PubKey" to publishableKey)

            val response = bg { "Init".httpPost().response() }.await()
            if (response.second.statusCode != 200) return false

            return true
        }

        suspend fun initPhoneVerification(phoneNumber: String) : ApiResponse {
            val response = bg { "SendVerificationCode".httpPost(listOf(Pair("phone", phoneNumber), Pair("country", "gh"))).response()}.await()
            return ApiResponse(response.second.statusCode == 200, Json.parse<ApiMessage>(String(response.second.data))!!)
        }

        fun generateMobileMoneyToken(params: MomoTokenParams, tokenOwnerDetails: TokenOwnerDetails): String {
            return ""
        }

        fun createToken() {
        }
    }
}