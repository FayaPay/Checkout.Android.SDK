package com.fayapay.checkout.presenters

open class CardPayPresenter(val view: View) {
    fun checkout() {
        view.notifyActionPerformed("checkout-completed")
    }

    interface View {
        fun notifyActionPerformed(action: String)
    }
}