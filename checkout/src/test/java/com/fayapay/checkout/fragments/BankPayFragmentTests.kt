package com.fayapay.checkout.fragments

import com.fayapay.checkout.presenters.BankPayPresenter
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations

class BankPayFragmentTests {
    private lateinit var presenter: BankPayPresenter
    private lateinit var view: BankPayPresenter.View

    @Captor
    lateinit var captor: ArgumentCaptor<String>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        view = mock()
        presenter = BankPayPresenter(view)
    }

    @Test
    fun itCompletesCheckoutByCallingActionListener(){
        presenter.checkout()

        verify(view).notifyActionPerformed(capture(captor))
        assertEquals("checkout-complete", captor.value)
    }
}