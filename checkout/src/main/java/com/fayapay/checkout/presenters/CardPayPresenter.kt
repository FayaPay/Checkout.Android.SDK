package com.fayapay.checkout.presenters

class CardPayPresenter(private val view: View) {
    fun checkout() = view.notifyActionPerformed("checkout-completed")

    fun navigateBack() = view.notifyActionPerformed("navigate-back")

    interface View {
        fun notifyActionPerformed(action: String)
    }
}