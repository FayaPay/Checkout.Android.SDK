package com.fayapay.checkout

import com.fayapay.checkout.api.data.TokenOwnerDetails

internal class CheckoutData {
    companion object {
        var ownerDetails: TokenOwnerDetails? = null

        fun clear(){
            ownerDetails = null
        }
    }
}