package com.fayapay.checkout

import android.support.annotation.DrawableRes

class CheckoutParams(val amount: Int, val currency: String, val description: String, @DrawableRes val icon: Int?) {}