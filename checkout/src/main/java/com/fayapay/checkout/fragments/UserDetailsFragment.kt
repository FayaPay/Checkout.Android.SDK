package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fayapay.checkout.R
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : CheckoutStage() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueBtn.setOnClickListener {
            listener.actionPerformed("choose-method")
        }
    }
}
