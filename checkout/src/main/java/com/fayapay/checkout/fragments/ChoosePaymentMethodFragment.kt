package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_choose_payment_method.*

internal class ChoosePaymentMethodFragment() : CheckoutStage() {
    private var nextStep: String = "credit-card"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditCardMethod.setOnFocusChangeListener { _, focused ->
            if (focused) nextStep = "credit-card"
        }
        bankMethod.setOnFocusChangeListener {_, focused ->
            if (focused) nextStep = "bank"
        }
        mobileMoneyMethod.setOnFocusChangeListener {_, focused ->
            if (focused) nextStep = "mobile-money"
        }

        continueBtn.setOnClickListener {
            listener.actionPerformed(nextStep)
        }
    }
}
