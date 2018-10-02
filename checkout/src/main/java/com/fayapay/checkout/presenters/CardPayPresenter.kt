package com.fayapay.checkout.presenters

internal open class CardPayPresenter(val view: View) {
    fun checkout() {
        view.notifyActionPerformed("checkout-completed")
    }

    interface View {
        fun notifyActionPerformed(action: String)
    }
}