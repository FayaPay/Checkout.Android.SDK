package com.fayapay.checkout.exceptions

class FayaPayInvalidParameterException(val parameter: String, val error: String) : FayaPayException("$parameter $error") {
}