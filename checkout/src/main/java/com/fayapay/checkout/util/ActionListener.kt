package com.fayapay.checkout.util

internal interface ActionListener {
    fun actionPerformed(type: String): Unit
}