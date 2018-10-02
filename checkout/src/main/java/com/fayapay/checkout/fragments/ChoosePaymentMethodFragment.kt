package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.OvershootInterpolator
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.ChoosePaymentMethodPresenter
import com.fayapay.checkout.util.CheckoutStage
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.fragment_choose_payment_method.*

internal class ChoosePaymentMethodFragment() : CheckoutStage(), ChoosePaymentMethodPresenter.View {
    private val presenter = ChoosePaymentMethodPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creditCardMethod.setOnFocusChangeListener { _, focused ->
            presenter.setNextStep(focused, "credit-card", creditCardMethod)
        }
        bankMethod.setOnFocusChangeListener {_, focused ->
            presenter.setNextStep(focused, "bank", bankMethod)
        }
        mobileMoneyMethod.setOnFocusChangeListener {_, focused ->
            presenter.setNextStep(focused, "mobile-money", mobileMoneyMethod)
        }

        continueBtn.setOnClickListener {
            presenter.gotoNextStep()
        }
    }

    override fun doNavigatedToAnimation() {
        ViewAnimator.animate(cardView, continueBtn, instructionLbl)
                .slideBottom()
                .interpolator(AnticipateOvershootInterpolator())
                .duration(700)
                .start()
    }

    override fun performAction(action: String) {
        listener.actionPerformed(action)
    }

    override fun scaleUp(view: View){
        ViewAnimator.animate(view)
                .startDelay(100)
                .scale(1.1f)
                .duration(600)
                .interpolator(OvershootInterpolator())
                .start()
    }

    override fun scaleDown(view: View){
        ViewAnimator.animate(view)
                .scale(1.0f)
                .duration(300)
                .accelerate()
                .start()
    }
}
