package com.fayapay.checkout.api.data

internal class MomoTokenParams(val channel: String, country: String,
                               val amount: Int, val currency: String,
                               val phoneNumber: String, val verificationToken: String) {
}