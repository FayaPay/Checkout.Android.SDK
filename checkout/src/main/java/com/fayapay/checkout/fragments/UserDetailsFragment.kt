package com.fayapay.checkout.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.UserDetailsPresenter
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_user_details.*

internal class UserDetailsFragment() : CheckoutStage(), UserDetailsPresenter.View {
    private val presenter = UserDetailsPresenter(this)

    override val name: String
        get() = nameTb.text.toString()

    override val email: String
        get() = emailTb.text.toString()

    override val phone: String
        get() = phoneTb.text.toString()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkoutDescription.text = arguments!!.get("description").toString()
        checkoutImage.setImageDrawable(activity!!.resources.getDrawable(arguments!!.getInt("icon")))

        continueBtn.setOnClickListener { presenter.showPaymentMethods() }
        backBtn.setOnClickListener { presenter.navigateBack() }
    }

    override fun notifyActionPerformed(action: String) {
        listener.actionPerformed(action)
    }
}
