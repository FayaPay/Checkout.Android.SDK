package com.fayapay.checkout.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.fayapay.checkout.fragments.CardPayFragment
import com.fayapay.checkout.fragments.MomoPayFragment
import com.fayapay.checkout.fragments.MoreMethodsFragment

class PaymentMethodsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val pages = listOf(CardPayFragment(), MomoPayFragment(), MoreMethodsFragment())

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Card"
            1 -> return "Mobile money"
            2 -> return "+More"
            else -> return super.getPageTitle(position)
        }
    }
}