package com.fayapay.checkout.presenters

open class BankPayPresenter(val view: View) {
    fun checkout() {
        view.notifyActionPerformed("checkout-complete")
    }

    interface View {
        fun notifyActionPerformed(action: String)
    }
}