package com.fayapay.checkout.presenters

internal class UserDetailsPresenter(private val view: View) {
    fun showPaymentMethods(){
        view.notifyActionPerformed("user-details-entered")
    }

    interface View{
        fun notifyActionPerformed(action: String)
    }
}