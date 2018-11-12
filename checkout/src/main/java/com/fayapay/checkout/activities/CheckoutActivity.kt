package com.fayapay.checkout.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import org.jetbrains.anko.coroutines.experimental.bg
import java.util.*

internal class CheckoutActivity() : AppCompatActivity(), ActionListener {
    private lateinit var params: CheckoutParams
    private val pages = mutableListOf<CheckoutStage>()
    private val navigationStack = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        params = CheckoutParams(intent.getIntExtra("amount", 0),
                intent.getStringExtra("currency"), intent.getStringExtra("description"),
                intent.getIntExtra("icon", R.drawable.input_bg))

        setupPages()
        viewpager.adapter = CheckoutPagerAdapter(supportFragmentManager, pages, this)

        initializeApi()
    }

    private fun initializeApi() = launch(CommonPool) {
        val publishableKey = intent.getStringExtra("publishableKey")
        val initSucceeded = FayaPayApi.initialize(publishableKey)

        launch(UI) {
            val intent = Intent()
            intent.putExtra("message", "Invalid publishable key")
            intent.putExtra("status", "failed")

            if(!initSucceeded) {
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
            }
        }
    }

    private fun setupPages() {
        val userDetailsFragment = UserDetailsFragment()
        val bundle = Bundle()

        val icon = if (params.icon == null) R.drawable.ic_bank else params.icon!!

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

            "checkout-completed" -> finalizeCheckout()
        }
    }

    override fun onBackPressed() {
        if (!navigationStack.empty()) viewpager.currentItem = navigationStack.pop()
        else finalizeCheckout()
    }

    private fun finalizeCheckout() {

    }
}
