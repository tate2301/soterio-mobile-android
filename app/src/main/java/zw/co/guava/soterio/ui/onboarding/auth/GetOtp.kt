package zw.co.guava.soterio.ui.onboarding.auth

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_get_otp.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main_apk.MainActivity


class GetOtp : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)

        mAuth = FirebaseAuth.getInstance();

        val phoneNumber = findViewById<TextInputEditText>(R.id.phoneNumberTextField)
        val phoneNumberLayout = findViewById<TextInputLayout>(R.id.phoneNumberLayout)
        val getOtpBtn = findViewById<Button>(R.id.getOtpButton)

        getOtpBtn.setOnClickListener {

            when {
                (phoneNumber.text?.toList()?.size!! == 9) -> {
                    indeterminateBar.visibility = View.VISIBLE
                    MaterialAlertDialogBuilder(this)
                        .setTitle(resources.getString(R.string.get_otp_title))
                        .setMessage(resources.getString(R.string.get_otp_supporting_text) + Html.fromHtml("<b> +263" +phoneNumber.text + "</b>"))
                        .setNegativeButton(resources.getString(R.string.get_otp_change_number)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton(resources.getString(R.string.proceed)) { dialog, _ ->
                            val intent = Intent(baseContext, VerifyPhone::class.java)
                            intent.putExtra("PHONE_NUMBER", "+263${phoneNumber.text}");
                            startActivity(intent)
                            dialog.dismiss()
                            indeterminateBar.visibility = View.GONE
                        }
                        .show()
                    phoneNumberLayout.error = null
                }

                (phoneNumber.text?.toList()?.size!! < 9) -> {
                    phoneNumberLayout.error = "Phone number too short!"
                }

                (phoneNumber.text.isNullOrBlank()) -> {
                    phoneNumberLayout.error = "Phone number is required!"
                }

                else -> {
                    phoneNumberLayout.error = null
                }
            }
        }

    }

    private fun acknowledgeAuthentication(currentUser: FirebaseUser?) {
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exitFromApp()
    }

    private fun exitFromApp() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }


}
