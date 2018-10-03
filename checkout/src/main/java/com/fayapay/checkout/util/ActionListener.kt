package com.fayapay.checkout.util

internal interface ActionListener {
    fun actionPerformed(action: String): Unit
}