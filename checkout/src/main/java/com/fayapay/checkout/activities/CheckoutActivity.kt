package com.fayapay.checkout.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fayapay.checkout.CheckoutParams
import com.fayapay.checkout.R
import com.fayapay.checkout.adapters.CheckoutPagerAdapter
import com.fayapay.checkout.fragments.PaymentMethodFragment
import com.fayapay.checkout.fragments.UserDetailsFragment
import com.fayapay.checkout.util.ActionListener
import com.fayapay.checkout.util.CheckoutStage
import kotlinx.android.synthetic.main.activity_checkout.*
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

            "checkout-completed" -> finalizeCheckout()
        }
    }

    override fun onBackPressed() {
        if (!navigationStack.empty()) viewpager.currentItem = navigationStack.pop()
        else finalizeCheckout()
    }

    private fun finalizeCheckout() {
        //setResult(intent.getIntExtra("requestCode", 0), null)
        finish()
    }
}
