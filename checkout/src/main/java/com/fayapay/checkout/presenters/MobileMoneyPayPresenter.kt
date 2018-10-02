package com.fayapay.checkout.presenters

open class MobileMoneyPayPresenter(val view: View) {
    fun checkout() {
        view.notifyActionPerformed("checkout-complete")
    }

    interface View {
        fun notifyActionPerformed(action: String)
    }
}