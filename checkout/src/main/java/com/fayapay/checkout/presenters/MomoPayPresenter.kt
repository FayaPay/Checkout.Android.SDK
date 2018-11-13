package com.fayapay.checkout.presenters

import com.fayapay.checkout.Checkout
import com.fayapay.checkout.api.FayaPayApi
import com.fayapay.checkout.api.data.MomoTokenParams
import com.fayapay.checkout.api.responses.ErrorResponse
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MomoPayPresenter(private val view: View) {
    private var verificationCodeSent = false;

    fun checkout() {
        if (!verificationCodeSent) {
            val success = view.validatePhone()
            if (success) {
                view.setLoading(true)
                sendVerificationCode()
            }
        } else {
            val success = view.validateVerificationCode()
            if (success) {
                view.setLoading(true)
                createMomoSource()
            }
        }

    }

    fun navigateBack() = view.notifyActionPerformed("navigate-back");

    private fun createMomoSource() = launch(CommonPool) {
        val response = FayaPayApi.generateMobileMoneyToken(MomoTokenParams(view.network.toLowerCase(), "gh", 100, "ghc", view.phone, view.verificationCode),
                Checkout.data.ownerDetails!!)

        launch(UI) {
            if (response.success) {
                Checkout.data.source = response.getData()
                Checkout.complete(response)
                view.notifyActionPerformed("checkout-completed")
            } else view.showError(response.getData<ErrorResponse>()!!)

            view.setLoading(false)
        }
    }

    private fun sendVerificationCode() = launch(CommonPool) {
        val response = FayaPayApi.initPhoneVerification(view.phone)
        launch(UI) {
            if (response.success) {
                verificationCodeSent = true
                view.showVerificationLayout()
            }
            view.setLoading(false)
        }
    }

    interface View {
        val network: String
        val phone: String
        val verificationCode: String
        fun notifyActionPerformed(action: String)
        fun validatePhone(): Boolean
        fun validateVerificationCode(): Boolean
        fun setLoading(loading: Boolean)
        fun showVerificationLayout()
        fun showError(error: ErrorResponse)
    }

}