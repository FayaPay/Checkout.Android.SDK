package com.fayapay.checkout.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.fayapay.checkout.fragments.CardPayFragment

class PaymentMethodsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val pages = listOf(CardPayFragment())

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Card"
            else -> return super.getPageTitle(position)
        }
    }
}