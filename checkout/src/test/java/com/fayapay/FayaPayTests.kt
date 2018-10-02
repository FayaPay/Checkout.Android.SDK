import com.fayapay.checkout.FayaPay
import com.fayapay.checkout.exceptions.FayaPayInitializationException
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class FayaPayTests{
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test(expected = FayaPayInitializationException::class)
    fun itWontCheckoutIfUninitialized_Fayapay(){
        FayaPay.instance.checkout(mock())
    }

    @Test
    fun itWillCorrectlyInitialize(){
        FayaPay.instance.initialize(mock())
        Assert.assertEquals(true, FayaPay.instance.initialized)
    }
}