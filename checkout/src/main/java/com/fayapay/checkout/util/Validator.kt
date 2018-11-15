package com.fayapay.checkout.util

import android.Manifest
import android.content.pm.PackageManager
import android.content.Context

class Validator {
    companion object {
        fun hasInternetPermission(context: Context) : Boolean {
            val pm = context.packageManager
            val hasPermission = pm.checkPermission(Manifest.permission.INTERNET, context.packageName)//context.checkCallingOrSelfPermission(Manifest.permission.INTERNET);
            return hasPermission == PackageManager.PERMISSION_DENIED
        }
    }

}