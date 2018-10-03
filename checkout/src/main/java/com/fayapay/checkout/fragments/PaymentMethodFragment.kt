package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fayapay.checkout.R
import com.fayapay.checkout.util.CheckoutStage

internal class PaymentMethodFragment : CheckoutStage() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }


}
