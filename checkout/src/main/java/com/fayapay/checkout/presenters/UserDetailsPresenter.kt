package com.fayapay.checkout.presenters

internal class UserDetailsPresenter(private val view: View) {
    fun showPaymentMethods() = view.notifyActionPerformed("user-details-entered")

    fun navigateBack() = view.notifyActionPerformed("navigate-back")

    interface View{
        fun notifyActionPerformed(action: String)
    }
}