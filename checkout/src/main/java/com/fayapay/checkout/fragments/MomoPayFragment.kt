package com.fayapay.checkout.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fayapay.checkout.R
import com.fayapay.checkout.presenters.MomoPayPresenter
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.fragment_momo_pay.*

internal class MomoPayFragment : CheckoutStage(), MomoPayPresenter.View {
    private val presenter = MomoPayPresenter(this)
    override val network: String
        get() = networksSpinner.selectedItem.toString()

    override val phone: String
        get() = phoneTb.text.toString()

    override val verificationCode: String
        get() = verificationCodeTb.text.toString()

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

    override fun validatePhone(): Boolean {
        if (phone.isEmpty()) {
            Snackbar.make(phoneTb, "Phone number cannot be empty", Snackbar.LENGTH_LONG).show()
            return false;
        } else if (phone.matches(Regex.fromLiteral("(\\+233|233|0)[2|5][0|4|6|7]\\d{7}"))) {
            Snackbar.make(phoneTb, "Phone number is invalid", Snackbar.LENGTH_LONG).show()
            return false;
        }
        else return true
    }

    override fun validateVerificationCode(): Boolean {
        if(verificationCode.isEmpty()){
            Snackbar.make(phoneTb, "Verification code cannot be empty", Snackbar.LENGTH_LONG).show()
            return false;
        }

        return true;
    }

    override fun setLoading(loading: Boolean) {
        if (loading) {
            notifyActionPerformed("show-loading")
            continueBtn.isEnabled = false
            backBtn.isEnabled = false
        } else {
            notifyActionPerformed("hide-loading")
            continueBtn.isEnabled = true
            backBtn.isEnabled = true
        }
    }

    override fun showVerificationLayout() {
        networksSpinner.isEnabled = false;
        phoneTb.isEnabled = false;

        textView3.visibility = View.VISIBLE;
        verificationCodeTb.visibility = View.VISIBLE;
    }
}