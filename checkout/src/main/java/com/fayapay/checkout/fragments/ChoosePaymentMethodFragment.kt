package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.OvershootInterpolator
import com.fayapay.checkout.R
import com.fayapay.checkout.util.CheckoutStage
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.fragment_choose_payment_method.*

internal class ChoosePaymentMethodFragment() : CheckoutStage() {
    private var nextStep: String = "credit-card"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditCardMethod.setOnFocusChangeListener { _, focused ->
            setNextStep(focused, "credit-card", creditCardMethod)
        }
        bankMethod.setOnFocusChangeListener {_, focused ->
            setNextStep(focused, "bank", bankMethod)
        }
        mobileMoneyMethod.setOnFocusChangeListener {_, focused ->
            setNextStep(focused, "mobile-money", mobileMoneyMethod)
        }

        continueBtn.setOnClickListener {
            listener.actionPerformed(nextStep)
        }
    }

    override fun doNavigatedToAnimation() {
        ViewAnimator.animate(choiceCardView, continueBtn, chooseMethodInstructionLbl)
                .slideBottom()
                .interpolator(AnticipateOvershootInterpolator())
                .duration(700)
                .start()
    }

    private fun setNextStep(focused: Boolean, nextStep : String, view : View){
        if (focused){
            this.nextStep = nextStep;
            scaleUp(view)
        }else{
            scaleDown(view)
        }
    }

    private fun scaleUp(view: View){
        ViewAnimator.animate(view)
                .startDelay(100)
                .scale(1.1f)
                .duration(600)
                .interpolator(OvershootInterpolator())
                .start()
    }

    private fun scaleDown(view: View){
        ViewAnimator.animate(view)
                .scale(1.0f)
                .duration(300)
                .accelerate()
                .start()
    }
}
