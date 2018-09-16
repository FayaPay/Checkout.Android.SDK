package com.fayapay.sdktest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fayapay.checkout.FayaPay
import com.fayapay.checkout.activities.CheckoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkoutBtn.setOnClickListener {
            FayaPay.instance.checkout(this);
        }
    }
}
