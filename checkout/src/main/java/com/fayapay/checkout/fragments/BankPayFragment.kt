package com.fayapay.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.BankPayPresenter
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.fragment_bank_pay.*

internal class BankPayFragment : BankPayPresenter(), BankPayPresenter.VIEW {
    private val presenter = BankPayPresenter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bank_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkoutBtn.setOnClickListener {
            presenter.checkout()
        }
    }

    override fun doNavigatedToAnimation() {
        ViewAnimator.animate(cardView, checkoutBtn, instructionLbl)
                .slideBottom()
                .interpolator(AnticipateOvershootInterpolator())
                .duration(700)
                .start()
    }

    override fun checkoutComplete() {
        listener.actionPerformed("checkout-completed")
    }

}
