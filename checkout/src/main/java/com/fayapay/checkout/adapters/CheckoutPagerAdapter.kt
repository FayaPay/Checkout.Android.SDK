package com.fayapay.checkout.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.fayapay.checkout.util.ActionListener
import android.support.v4.app.FragmentManager

class CheckoutPagerAdapter(val pages: List<CheckoutStage>, fm: FragmentManager, actionListener: ActionListener) : FragmentStatePagerAdapter(fm) {

    init {
        pages.forEach { stage -> stage.setActionListener(actionListener) }
    }

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size
}