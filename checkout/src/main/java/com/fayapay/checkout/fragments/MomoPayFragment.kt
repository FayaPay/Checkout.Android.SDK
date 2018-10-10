package com.fayapay.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.MomoPayPresenter
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_momo_pay.*

internal class MomoPayFragment : CheckoutStage(), MomoPayPresenter.View {
    private val presenter = MomoPayPresenter(this)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_momo_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continueBtn.setOnClickListener { presenter.checkout() }
        backBtn.setOnClickListener { presenter.navigateBack() }
    }

    override fun notifyActionPerformed(action: String) {
        listener.actionPerformed(action)
    }
}