package com.fayapay.checkout.presenters

import android.support.v4.app.Fragment
import com.fayapay.checkout.util.ActionListener

open class BasePresenter : Fragment() {
    internal lateinit var listener: ActionListener

    open fun setActionListener(actionListener: ActionListener) {
        this.listener = actionListener
    }

    open fun doNavigatedToAnimation(){}
    open fun attachView(){}
    open fun detachView(){}
}