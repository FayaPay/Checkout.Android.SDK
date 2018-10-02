import com.fayapay.checkout.FayaPay
import com.fayapay.checkout.exceptions.FayaPayInitializationException
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

class FayaPayTests {
    @Test(expected = FayaPayInitializationException::class)
    fun itWontCheckoutIfUninitialized_Fayapay() {
        FayaPay.checkout(mock())
    }
}