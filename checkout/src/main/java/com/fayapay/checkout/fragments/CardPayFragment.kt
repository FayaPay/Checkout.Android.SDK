package com.fayapay.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.util.CheckoutStage

internal class CardPayFragment : CheckoutStage() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_pay, container, false)
    }
}