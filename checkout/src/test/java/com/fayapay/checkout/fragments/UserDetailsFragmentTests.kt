package com.fayapay.checkout.fragments

import com.fayapay.checkout.presenters.UserDetailsPresenter
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations

class UserDetailsFragmentTests {
    private lateinit var presenter: UserDetailsPresenter
    private lateinit var view: UserDetailsPresenter.View

    @Captor
    private lateinit var captor : ArgumentCaptor<String>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        view = mock()
        presenter = UserDetailsPresenter(view)
    }

    @Test
    fun itShouldCorrectlyNavigateToChooseMethodFragment(){

    }
}