package com.fayapay.checkout

import com.fayapay.checkout.api.data.TokenOwnerDetails
import com.fayapay.checkout.api.responses.ApiResponse
import com.fayapay.checkout.api.responses.CreateMomoSourceResponse

internal class Checkout {
    companion object {
        val data = CheckoutData()
        lateinit var finalResponse: ApiResponse

        fun complete(apiResponse: ApiResponse) {
            this.finalResponse = apiResponse
        }
    }

    internal class CheckoutData {
        var ownerDetails: TokenOwnerDetails? = null
        var source: CreateMomoSourceResponse? = null
        var params: CheckoutParams? = null

        fun clear() {
            ownerDetails = null
            source = null
        }
    }
}