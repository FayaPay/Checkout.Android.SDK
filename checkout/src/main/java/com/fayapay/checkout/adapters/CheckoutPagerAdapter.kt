package com.fayapay.checkout.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.fayapay.checkout.util.ActionListener
import com.fayapay.checkout.util.CheckoutStage

internal class CheckoutPagerAdapter(fm: FragmentManager, private val pages: List<CheckoutStage>,
                                    private val listener: ActionListener) : FragmentStatePagerAdapter(fm) {

    init{
        pages.forEach { it.setActionListener(listener) }
    }

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size
}