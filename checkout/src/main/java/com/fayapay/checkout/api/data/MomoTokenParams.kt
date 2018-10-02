package com.fayapay.checkout.api.data

internal class MomoTokenParams(val channel: String, val phoneNumber: String,
                               val amount: Int, val currency: String,
                               country: String, val verificationToken: String) {
}