package com.fayapay.checkout.presenters

class CardPayPresenter(private val view: View) {
    fun checkout(){
        view.notifyActionPerformed("checkout-completed")
    }

    interface View{
        fun notifyActionPerformed(action: String)
    }
}