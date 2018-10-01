package com.fayapay.checkout.presenters

import com.fayapay.checkout.util.ActionListener

open class BankPayPresenter : BasePresenter() {
    private lateinit var view: VIEW
    fun checkout(){
        view.checkoutComplete()
    }

    interface VIEW{
        fun checkoutComplete()
    }
}