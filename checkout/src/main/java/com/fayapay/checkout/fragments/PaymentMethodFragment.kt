package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.adapters.PaymentMethodsPagerAdapter
import com.fayapay.checkout.util.ActionListener
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_payment_method.*

internal class PaymentMethodFragment : CheckoutStage(), ActionListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = PaymentMethodsPagerAdapter(this, childFragmentManager)
        tabLayout.setupWithViewPager(viewpager)
    }

    override fun actionPerformed(action: String) {
        listener.actionPerformed(action)
    }
}
