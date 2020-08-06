package zw.co.guava.soterio.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verify_personal_details.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.permissions.enable_bluetooth
import zw.co.guava.soterio.ui.permissions.privacy_policy

class VerifyAddress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_address)

        nextButton.setOnClickListener {
            val intent = Intent(baseContext, privacy_policy::class.java)
            startActivity(intent)
        }
    }
}