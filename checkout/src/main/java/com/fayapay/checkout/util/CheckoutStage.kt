package com.fayapay.checkout.util

import android.support.v4.app.Fragment

open class CheckoutStage : Fragment() {
    internal lateinit var listener: ActionListener

    open fun setActionListener(actionListener: ActionListener) {
        this.listener = actionListener
    }
}