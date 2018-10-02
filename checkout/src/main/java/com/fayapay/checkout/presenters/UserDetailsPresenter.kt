package com.fayapay.checkout.presenters

internal open class UserDetailsPresenter(val view: View) {
    fun gotoChooseMethod() {
        view.performAction("choose-method")
    }

    interface View {
        fun performAction(action: String)
    }
}