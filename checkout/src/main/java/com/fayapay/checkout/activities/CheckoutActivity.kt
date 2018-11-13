package com.fayapay.checkout.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.fayapay.checkout.Checkout
import com.fayapay.checkout.R
import com.fayapay.checkout.adapters.CheckoutPagerAdapter
import com.fayapay.checkout.api.FayaPayApi
import com.fayapay.checkout.exceptions.FayaPayCheckoutException
import com.fayapay.checkout.fragments.PaymentMethodFragment
import com.fayapay.checkout.fragments.UserDetailsFragment
import com.fayapay.checkout.util.ActionListener
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*

internal class CheckoutActivity() : AppCompatActivity(), ActionListener {
    private val params = Checkout.data.params!!
    private val pages = mutableListOf<CheckoutStage>()
    private val navigationStack = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        setupPages()
        viewpager.adapter = CheckoutPagerAdapter(supportFragmentManager, pages, this)

        initialize()
    }

    private fun initialize() = launch(CommonPool) {
        val publishableKey = intent.getStringExtra("publishableKey")
        val initSucceeded = FayaPayApi.initialize(publishableKey)

        launch(UI) {
            if (!initSucceeded) throw FayaPayCheckoutException("Checkout initialization failed. Please ensure your publishable key is valid.")
        }
    }

    private fun setupPages() {
        val userDetailsFragment = UserDetailsFragment()
        val bundle = Bundle()

        val icon = if (params.icon == null) R.drawable.ic_bank else params.icon

        bundle.putInt("icon", icon)
        bundle.putString("description", params.description)
        userDetailsFragment.arguments = bundle

        pages.add(userDetailsFragment)
        pages.add(PaymentMethodFragment())
    }

    override fun actionPerformed(action: String) {
        launch(UI) {
            when (action) {
                "user-details-entered" -> {
                    viewpager.currentItem = 1
                    navigationStack.push(0)
                }

                "navigate-back" -> onBackPressed()

                "show-loading" -> loadingCircle.visibility = View.VISIBLE

                "hide-loading" -> loadingCircle.visibility = View.GONE

                "checkout-completed" -> finalizeCheckout()
            }
        }
    }

    override fun onBackPressed() {
        if (!navigationStack.empty()) viewpager.currentItem = navigationStack.pop()
        else {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun finalizeCheckout() {
        val intent = Intent()
        intent.putExtra("sourceId", Checkout.finalResponse.get<String>("id"))

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
