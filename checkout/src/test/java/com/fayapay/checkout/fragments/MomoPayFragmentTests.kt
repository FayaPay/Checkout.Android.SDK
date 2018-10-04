package com.fayapay.checkout.fragments

import com.fayapay.checkout.presenters.CardPayPresenter
import com.fayapay.checkout.presenters.MomoPayPresenter
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations

class MomoPayFragmentTests {
    private lateinit var presenter : MomoPayPresenter
    private lateinit var view : MomoPayPresenter.View

    @Captor
    private lateinit var captor : ArgumentCaptor<String>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        view = mock()
        presenter = MomoPayPresenter(view)
    }

    @Test
    fun itShouldCompleteCheckoutCorrectly(){
        presenter.checkout()

        verify(view).notifyActionPerformed(capture(captor))
        Assert.assertEquals("checkout-completed", captor.value)
    }
}