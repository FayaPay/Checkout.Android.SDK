package com.fayapay.checkout.presenters

internal open class ChoosePaymentMethodPresenter(val view: View) {
    var nextStep: String = "credit-card"

    fun gotoNextStep() {
        view.performAction(nextStep)
    }

    fun setNextStep(focused: Boolean, nextStep: String, view: android.view.View) {
        if (focused) {
            this.nextStep = nextStep;
            this.view.scaleUp(view)
        } else {
            this.view.scaleDown(view)
        }
    }

    interface View {
        fun performAction(action: String)
        fun scaleUp(view: android.view.View)
        fun scaleDown(view: android.view.View)
    }
}