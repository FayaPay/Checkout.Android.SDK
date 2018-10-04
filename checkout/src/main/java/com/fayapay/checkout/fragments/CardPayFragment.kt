package com.fayapay.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.CardPayPresenter
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_card_pay.*

internal class CardPayFragment : CheckoutStage(), CardPayPresenter.View {
    private val presenter = CardPayPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continueBtn.setOnClickListener {
            presenter.checkout()
        }
    }

    override fun notifyActionPerformed(action: String) {
        listener.actionPerformed(action)
    }
}