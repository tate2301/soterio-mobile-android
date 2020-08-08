package zw.co.guava.soterio.ui.onboarding.auth

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_verify_phone.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main_apk.MainActivity


class VerifyPhone : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)
        mAuth = FirebaseAuth.getInstance();
        val currentUser: FirebaseUser? = mAuth.currentUser;
        if(currentUser != null) {
            acknowledgeAuthentication(currentUser)
        }

        val phoneNumber = intent.getStringExtra(getString(R.string.phone_number))

        resendCodeLink.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.get_otp_title))
                .setMessage(getString(R.string.back_to_prev_screen))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.go_back)) { dialog, _ ->
                    super.onBackPressed()
                    dialog.dismiss()
                }
                .show()
        }

        verifyButton.setOnClickListener {
            val intent = Intent(baseContext, VerifyAddress::class.java)
            startActivity(intent)

        }
    }

    private fun acknowledgeAuthentication(currentUser: FirebaseUser?) {
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)
    }


}