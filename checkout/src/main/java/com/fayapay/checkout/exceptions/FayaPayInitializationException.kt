package com.fayapay.checkout.exceptions

class FayaPayInitializationException() : FayaPayException("FayaPay was never initialized. Please call 'FayaPay.initialize()' before performing any action.") {
}