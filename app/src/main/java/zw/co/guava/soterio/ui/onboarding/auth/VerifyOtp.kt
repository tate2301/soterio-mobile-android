package zw.co.guava.soterio.ui.onboarding.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import zw.co.guava.soterio.R

class VerifyOtp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        val getOtpBtn = findViewById<Button>(R.id.getOtpButton)

        getOtpBtn.setOnClickListener {
            val intent = Intent(baseContext, VerifyPhone::class.java)
            startActivity(intent)

        }
    }
}