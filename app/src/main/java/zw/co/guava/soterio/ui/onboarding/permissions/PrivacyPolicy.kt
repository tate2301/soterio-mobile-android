package zw.co.guava.soterio.ui.onboarding.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verify_personal_details.*
import zw.co.guava.soterio.R

class PrivacyPolicy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        nextButton.setOnClickListener {
            val intent = Intent(baseContext, EnableBluetooth::class.java)
            startActivity(intent)
        }
    }
}