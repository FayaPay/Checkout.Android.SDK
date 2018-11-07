package com.fayapay.checkout.api

import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.data.TokenOwnerDetails

internal interface IFayaPayApi {
    fun initPhoneVerification(phoneNumber: String)
    fun generateMobileMoneyToken(params: MomoTokenParams, tokenOwnerDetails: TokenOwnerDetails) : String
    fun createToken()
}