package com.fayapay.checkout.customviews

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

internal class NoSwipeViewPager(ctx: Context, attrs: AttributeSet?) : ViewPager(ctx, attrs) {
    constructor(ctx: Context) : this(ctx, null) {
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean = false
}