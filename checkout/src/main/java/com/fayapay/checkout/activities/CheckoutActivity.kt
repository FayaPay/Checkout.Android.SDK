package com.fayapay.checkout.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fayapay.checkout.R
import com.fayapay.checkout.adapters.CheckoutPagerAdapter
import com.fayapay.checkout.util.ActionListener
import kotlinx.android.synthetic.main.activity_checkout.*

internal class CheckoutActivity() : AppCompatActivity(), ActionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        supportActionBar?.title = "Checkout"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewpager.adapter = CheckoutPagerAdapter(supportFragmentManager, this)
    }

    override fun actionPerformed(action: String) {
        when(action){
            "user-details-completed" -> viewpager.currentItem = 1
        }
    }
}
