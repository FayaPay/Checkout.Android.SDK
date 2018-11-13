# fayapay-checkout-sdk

## Install

```
repositories {
    jcenter()
}

dependencies {
    implementation 'com.fayapay.checkout:0.1.0'
}
```

## Requirements

## Getting started

#### 1. Internet permission

Ensure that your app has internet permissions by making sure the uses-permission line below is present in the AndroidManifest.xml.

`<uses-permission android:name="android.permission.INTERNET" />`

#### 2. Initialization

```
public class App : Application(){
    override fun onCreate() {
        super.onCreate()

        // You can get your publishable key from https://dashboard.fayapay.io/Developers
        FayaPay.initialize(this, "YOUR_PUBLISHABLE_KEY_HERE")
    }
}
```

#### 3. Checkout

Checkout for a fictional sports betting company "FayaBet" that's looking to allow customers to load money into their app.

```
class LoadAccountActivity : AppCompatActivity() {
    val loadAccountRequestCode = 1957;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_account)

        loadAccountBtn.setOnClickListener {
            FayaPay.checkout(this, checkoutRequestCode, CheckoutParams(amountEditText.text.toInt(), "ghc", "Load your account so you can stand the chance to win millions by betting on your favorite team!", R.drawable.fayabet_logo))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == loadAccountRequestCode) handleCheckoutResult(resultCode, data!!);
    }

    private fun handleCheckoutResult(resultCode: Int, data: Intent){
        when(resultCode){
            Activity.RESULT_OK -> {
                val sourceId = data.getStringExtra("sourceId")
                // Do something with source id
            }

            Activity.RESULT_CANCELED -> {
                // Do something when the user closes the Checkout activity without completing the checkout process
            }
        }
    }
}
```
