package com.fayapay.checkout

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Base64
import com.fayapay.checkout.activities.CheckoutActivity
import com.fayapay.checkout.exceptions.FayaPayInitializationException
import java.security.MessageDigest

class FayaPay {
    companion object {
        private var _instance: FayaPay? = null

        val instance: FayaPay
            get() {
                if (_instance == null)
                    _instance = FayaPay()

                return _instance!!
            }
    }

    private lateinit var packageName: String
    private lateinit var signingKeyHash: String
    private var _initialized: Boolean = false

    val initialized : Boolean
    get() = initialized

    fun initialize(app: Application) {
        this.packageName = app.packageName

        val info = app.packageManager.getPackageInfo(app.packageName, PackageManager.GET_SIGNATURES)
        info.signatures.forEach {
            val digest = MessageDigest.getInstance("SHA")
            digest.update(it.toByteArray())
            this.signingKeyHash = Base64.encodeToString(digest.digest(), Base64.DEFAULT)
        }

        this._initialized = true
    }

    fun checkout(activity: Activity) {
        if (this._initialized)
            activity.startActivity(Intent(activity, CheckoutActivity::class.java))
        else throw FayaPayInitializationException("FayaPay was never initialized. Please call 'FayaPay.instance.initialize()' before performing any action.")
    }
}