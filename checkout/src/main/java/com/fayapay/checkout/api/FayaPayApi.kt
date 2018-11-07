package com.fayapay.checkout.api

import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.data.TokenOwnerDetails

internal class FayaPayApi : IFayaPayApi {
    override fun initPhoneVerification(phoneNumber: String) {

    }

    override fun generateMobileMoneyToken(params: MomoTokenParams, tokenOwnerDetails: TokenOwnerDetails): String {
        return ""
    }

    override fun createToken() {
    }
}