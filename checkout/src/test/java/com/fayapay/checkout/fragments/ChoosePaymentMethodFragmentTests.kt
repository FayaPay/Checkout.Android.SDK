package com.fayapay.checkout.fragments

import com.fayapay.checkout.presenters.ChoosePaymentMethodPresenter
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations

class ChoosePaymentMethodFragmentTests {
    private lateinit var presenter: ChoosePaymentMethodPresenter
    private lateinit var view: ChoosePaymentMethodPresenter.View

    @Captor
    lateinit var captor: ArgumentCaptor<String>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        view = mock()
        presenter = ChoosePaymentMethodPresenter(view)
    }

    @Test
    fun itCorrectlySetsNextStepOnPaymentMethodFocus() {
        presenter.setNextStep(true, "test-step", mock())

        Assert.assertEquals("test-step", presenter.nextStep)
    }

    @Test
    fun itCorrectlyCallsWithNextStep_actionListener() {
        presenter.setNextStep(true, "test-step", mock())
        presenter.gotoNextStep()

        verify(view).performAction(capture(captor))
        Assert.assertEquals("test-step", captor.value)
    }
}
