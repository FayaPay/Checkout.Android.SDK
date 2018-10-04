package com.fayapay.sdktest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fayapay.checkout.CheckoutParams
import com.fayapay.checkout.FayaPay
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FayaPay.initialize(application, "")

        checkoutBtn.setOnClickListener {
            FayaPay.checkout(this, 1957, CheckoutParams(1, "ghc", "Bich better have my money", R.drawable.ic_bank));
        }
    }
}
