import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.fayapay.checkout.CheckoutParams
import com.fayapay.checkout.FayaPay
import com.fayapay.checkout.exceptions.FayaPayInitializationException
import com.fayapay.checkout.exceptions.FayaPayInvalidParameterException
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class FayaPayTests {
    private lateinit var app: Application

    @Before
    fun setup(){
        app = mock()
        `when`(app.packageManager).thenReturn(mock())
        `when`(app.packageManager.getPackageInfo("com.fayapay.test", PackageManager.GET_SIGNATURES)).thenReturn(mock())

        FayaPay.reset()
    }

    @Test(expected = FayaPayInitializationException::class)
    fun itWontCheckoutIfUninitialized_Fayapay() {
        FayaPay.checkout(mock(), 0, CheckoutParams(1, "ghc", "test", null))
    }

    @Test
    fun itWillCheckoutIfAmountIsGreaterThanZero(){
        FayaPay.initialize(app, "")
        FayaPay.checkout(mock(), 0, CheckoutParams(1, "ghc", "test", null))
    }

    @Test
    fun itWillCheckoutIfAmountIsNull(){
        FayaPay.initialize(app, "")
        FayaPay.checkout(mock(), 0, CheckoutParams(null, "ghc", "test", null))
    }

    @Test(expected = FayaPayInvalidParameterException::class)
    fun itWontCheckoutIfAmountIsLessThanZero(){
        FayaPay.initialize(app, "")
        FayaPay.checkout(mock(), 0, CheckoutParams(-1, "ghc", "test", null))
    }

    @Test(expected = FayaPayInvalidParameterException::class)
    fun itWontCheckoutIfAmountIsEqualToZero(){
        FayaPay.initialize(app, "")
        FayaPay.checkout(mock(), 0, CheckoutParams(0, "ghc", "test", null))
    }

}