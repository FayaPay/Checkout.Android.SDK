package com.fayapay.checkout.util

interface ActionListener {
    fun actionPerformed(type: String): Unit
}