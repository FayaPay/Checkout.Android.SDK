package com.fayapay.checkout

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import com.fayapay.checkout.activities.CheckoutActivity
import com.fayapay.checkout.exceptions.FayaPayException
import com.fayapay.checkout.exceptions.FayaPayInitializationException
import com.fayapay.checkout.exceptions.FayaPayInvalidParameterException
import java.security.MessageDigest

class FayaPay {
    private lateinit var packageName: String
    private lateinit var signingKeyHash: String
    private var publishableKey: String = ""

    companion object {
        private var _instance: FayaPay? = null

        val isInitialized: Boolean
            get() = _instance != null

        val instance: FayaPay
            get() {
                checkInitialized()

                return _instance!!
            }

        fun initialize(app: Application, publishableKey: String) {
            if (publishableKey.isEmpty()) throw FayaPayException("Publishable key cannot be empty.")
            if (isInitialized) return

            _instance = FayaPay()
            instance.publishableKey = publishableKey

            val digest = MessageDigest.getInstance("SHA1")
            val info = app.packageManager.getPackageInfo(app.packageName, PackageManager.GET_SIGNATURES) // todo: upgrade sdk and use get_signing_certificates
            info.signatures.forEach {
                digest.update(it.toByteArray())
            }

            instance.signingKeyHash = Base64.encodeToString(digest.digest(), Base64.DEFAULT) // todo change this to hex
            instance.packageName = app.packageName
        }

        fun checkout(activity: Activity, requestCode: Int, params: CheckoutParams) {
            checkInitialized()

            if (params.amount != null && params.amount <= 0)
                throw FayaPayInvalidParameterException("amount", "must be greater than zero")

            Checkout.data.params = params

            val intent = Intent(activity, CheckoutActivity::class.java)
            intent.putExtra("requestCode", requestCode)
            intent.putExtra("publishableKey", instance.publishableKey)

            activity.startActivityForResult(intent, requestCode)
        }

        fun complete() {
            checkInitialized()
        }

        fun getToken(bundle: Bundle): String {
            return "todo"
        }

        private fun checkInitialized() {
            if (!isInitialized)
                throw FayaPayInitializationException()
        }

        // this method is used to reset the singleton when testing
        internal fun reset() {
            _instance = null
        }
    }
}