package com.fayapay.checkout.api.data

internal data class MomoTokenParams(val channel: String, val country: String,
                               val amount: Int, val currency: String,
                               val phoneNumber: String, val verificationToken: String) {
}