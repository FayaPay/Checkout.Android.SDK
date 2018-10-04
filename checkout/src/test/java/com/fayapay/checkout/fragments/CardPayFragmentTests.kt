package com.fayapay.checkout.fragments

import com.fayapay.checkout.presenters.CardPayPresenter
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor

class CardPayFragmentTests {
    private lateinit var presenter : CardPayPresenter
    private lateinit var view : CardPayPresenter.View
    private val captor = ArgumentCaptor<String>()

    @Before
    fun setup(){
        view = mock()
        presenter = CardPayPresenter(view)
    }

    @Test
    fun itShouldCompleteCheckoutCorrectly(){
        presenter.checkout()
        assertEquals("checkout-completed", )
    }
}