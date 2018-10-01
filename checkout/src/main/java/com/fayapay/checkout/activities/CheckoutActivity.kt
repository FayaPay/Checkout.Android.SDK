package com.fayapay.checkout.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fayapay.checkout.R
import com.fayapay.checkout.adapters.CheckoutPagerAdapter
import com.fayapay.checkout.fragments.*
import com.fayapay.checkout.presenters.BasePresenter
import com.fayapay.checkout.util.ActionListener
import kotlinx.android.synthetic.main.activity_checkout.*
import java.util.*

internal class CheckoutActivity() : AppCompatActivity(), ActionListener {
    private var navigationStack: Stack<String> = Stack()
    private val pages = listOf(UserDetailsFragment(), ChoosePaymentMethodFragment(), CardPayFragment(), BankPayFragment(), MobileMoneyPayFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        supportActionBar?.title = "Checkout"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        checkoutViewPager.adapter = CheckoutPagerAdapter(pages, supportFragmentManager, this)
        checkoutViewPager.currentItem = 0
        indicator.currentStep = 0
    }

    override fun actionPerformed(type: String) {
        when (type) {
            "credit-card" -> {
                indicator.currentStep = 2
                checkoutViewPager.currentItem = 2
                navigationStack.push("11")
                pages[2].doNavigatedToAnimation()
            }

            "bank" -> {
                indicator.currentStep = 2
                checkoutViewPager.currentItem = 3
                navigationStack.push("11")
                pages[3].doNavigatedToAnimation()
            }

            "mobile-money" -> {
                indicator.currentStep = 2
                checkoutViewPager.currentItem = 4
                navigationStack.push("11")
                pages[4].doNavigatedToAnimation()
            }

            "choose-method" -> {
                indicator.currentStep = 1
                checkoutViewPager.currentItem = 1
                navigationStack.push("00")
                pages[1].doNavigatedToAnimation()
            }

            "checkout-complete" -> finishCheckout()
        }
    }

    private fun finishCheckout() {
        finish()
    }

    override fun onBackPressed() {
        navigateAway()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                navigateAway()
                true;
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun navigateAway() {
        if (navigationStack.empty())
            finish()
        else {
            val whereTo = navigationStack.pop().split("").filter { it.length > 0 }
            indicator.currentStep = whereTo[0].toInt()
            checkoutViewPager.currentItem = whereTo[1].toInt()
            pages[whereTo[1].toInt()].doNavigatedToAnimation()
        }

    }
}
