package com.fayapay.checkout.api

import com.beust.klaxon.Klaxon
import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.data.TokenOwnerDetails
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import org.jetbrains.anko.coroutines.experimental.bg

internal class FayaPayApi : IFayaPayApi {

    override fun initPhoneVerification(phoneNumber: String) {
    }

    override fun generateMobileMoneyToken(params: MomoTokenParams, tokenOwnerDetails: TokenOwnerDetails): String {
        return ""
    }

    override fun createToken() {
    }

    companion object {
        private val Json = Klaxon()

        suspend fun initialize(publishableKey: String): Boolean {
            FuelManager.instance.basePath = "https://public.fayapay.io/api/v1/checkout/"
            FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "X-PubKey" to publishableKey)

            val response = bg { "Init".httpPost().response() }.await()
            if (response.second.statusCode != 200) return false

            return true
        }
    }
}