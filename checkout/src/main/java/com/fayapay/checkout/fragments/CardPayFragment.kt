package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.CardPayPresenter
import com.fayapay.checkout.util.CheckoutStage
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.fragment_card_pay.*

internal class CardPayFragment : CheckoutStage(), CardPayPresenter.View {
    private val presenter = CardPayPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkoutBtn.setOnClickListener {
            listener.actionPerformed("checkout-complete")
        }
    }

    override fun doNavigatedToAnimation() {
        ViewAnimator.animate(cardView, checkoutBtn, instructionLbl)
                .slideBottom()
                .interpolator(AnticipateOvershootInterpolator())
                .duration(700)
                .start()
    }

    override fun notifyActionPerformed(action: String) {
        listener.actionPerformed(action)
    }
}
