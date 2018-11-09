package com.fayapay.checkout.api

import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.data.TokenOwnerDetails
import com.microsoft.aspnet.signalr.HubConnection
import com.microsoft.aspnet.signalr.HubConnectionBuilder
import com.microsoft.aspnet.signalr.LogLevel
import org.jetbrains.anko.coroutines.experimental.bg

internal class FayaPayApi : IFayaPayApi{
    private lateinit var connection : HubConnection
    private lateinit var publishableKey: String

    override fun initPhoneVerification(phoneNumber: String) {

    }

    override fun generateMobileMoneyToken(params: MomoTokenParams, tokenOwnerDetails: TokenOwnerDetails): String {
        return ""
    }

    override fun createToken() {
    }

    fun initialize(publishableKey: String) {
        this.publishableKey = publishableKey

        connection = HubConnectionBuilder().withUrl("https://public.fayapay.io/hub/checkout").configureLogging(LogLevel.Information).build()
        connection.onClosed { println("signal r disconnected") }

        bg{
            connection.start()
        }



    }
}