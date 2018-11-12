package com.fayapay.checkout.presenters

import com.fayapay.checkout.CheckoutData
import com.fayapay.checkout.api.data.TokenOwnerDetails

internal class UserDetailsPresenter(private val view: View) {
    fun showPaymentMethods(){
        CheckoutData.ownerDetails = TokenOwnerDetails(view.name, view.email, view.phone)
        view.notifyActionPerformed("user-details-entered")
    }

    fun navigateBack() = view.notifyActionPerformed("navigate-back")

    interface View{
        val name: String
        val email: String
        val phone: String
        fun notifyActionPerformed(action: String)
    }
}