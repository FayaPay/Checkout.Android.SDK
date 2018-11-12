package com.fayapay.checkout.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.beust.klaxon.Klaxon
import com.fayapay.checkout.Checkout
import com.fayapay.checkout.CheckoutParams
import com.fayapay.checkout.R
import com.fayapay.checkout.adapters.CheckoutPagerAdapter
import com.fayapay.checkout.api.FayaPayApi
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
            val intent = Intent()
            intent.putExtra("message", "Invalid publishable key")
            intent.putExtra("status", "failed")

            if (!initSucceeded) {
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
            }
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

    override fun onBackPressed() {
        if (!navigationStack.empty()) viewpager.currentItem = navigationStack.pop()
        else finalizeCheckout()
    }

    private fun finalizeCheckout() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
