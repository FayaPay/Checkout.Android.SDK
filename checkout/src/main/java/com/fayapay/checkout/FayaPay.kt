package com.fayapay.checkout

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import com.fayapay.checkout.activities.CheckoutActivity
import com.fayapay.checkout.exceptions.FayaPayInitializationException
import java.security.MessageDigest

class FayaPay {
    private lateinit var packageName: String
    private lateinit var signingKeyHash: String

    companion object {
        private var _instance: FayaPay? = null

        val isInitialized: Boolean
            get() = _instance != null

        val instance: FayaPay
            get() {
                checkInitialized()

                return _instance!!
            }

        fun initialize(app: Application) {
            if(!isInitialized) return

            _instance = FayaPay()

            val digest = MessageDigest.getInstance("SHA1")
            val info = app.packageManager.getPackageInfo(app.packageName, PackageManager.GET_SIGNATURES) // todo: upgrade sdk and use get_signing_certificates
            info.signatures.forEach {
                digest.update(it.toByteArray())
            }

            instance.signingKeyHash = Base64.encodeToString(digest.digest(), Base64.DEFAULT) // todo change this to hex
            instance.packageName = app.packageName
        }

        fun checkout(activity: Activity) {
            checkInitialized()

            activity.startActivity(Intent(activity, CheckoutActivity::class.java))
        }

        fun complete() {
            checkInitialized()
        }

        fun getToken(bundle: Bundle): String {
            return "todo"
        }

        private fun checkInitialized() {
            if (!isInitialized)
                throw FayaPayInitializationException("FayaPay was never initialized. Please call 'FayaPay.instance.initialize()' before performing any action.")

        }
    }


}