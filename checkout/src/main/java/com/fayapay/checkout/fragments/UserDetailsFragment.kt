package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator

import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.UserDetailsPresenter
import com.fayapay.checkout.util.CheckoutStage
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.fragment_user_details.*

internal class UserDetailsFragment : CheckoutStage(), UserDetailsPresenter.View {
    private val presenter = UserDetailsPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doNavigatedToAnimation()
        continueBtn.setOnClickListener {
            presenter.gotoChooseMethod()
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
}
