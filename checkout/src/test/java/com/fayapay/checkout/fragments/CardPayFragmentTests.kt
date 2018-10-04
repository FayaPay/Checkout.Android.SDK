package com.fayapay.checkout.fragments

import com.fayapay.checkout.presenters.CardPayPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations

class CardPayFragmentTests {
    private lateinit var presenter : CardPayPresenter
    private lateinit var view : CardPayPresenter.View

    @Captor
    private lateinit var captor : ArgumentCaptor<String>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        view = mock()
        presenter = CardPayPresenter(view)
    }

    @Test
    fun itShouldCompleteCheckoutCorrectly(){
        presenter.checkout()
        verify("checkout-completed", view.notifyActionPerformed(captor.value))
    }
}