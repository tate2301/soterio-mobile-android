package zw.co.guava.soterio.ui.onboarding.auth

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_verify_phone.*
import zw.co.guava.soterio.R
import java.util.concurrent.TimeUnit


class VerifyPhone : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var storedVerificationId: String;
    private lateinit var forceResendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)
        mAuth = FirebaseAuth.getInstance();
        val currentUser: FirebaseUser? = mAuth.currentUser;
        if(currentUser != null) {
            acknowledgeAuthenticationWithServer(currentUser)
        }

        val phoneNumber = intent.getStringExtra(getString(R.string.phone_number)).toString()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            authCallbacks
        )

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

        // Start the verification process on code input
        verifyButton.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, otpInput.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private val authCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            Log.d("FirebaseAuth", "OnVerificationCompleted:${p0}")
            signInWithPhoneAuthCredential(p0);
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("FirebaseAuth", "OnVerificationFailed: ${p0.message}")

            if(p0 is FirebaseAuthInvalidCredentialsException) {
                Log.d("FirebaseAuth", "OnVerificationFailed: FirebaseAuthInvalidCredentials")
            } else if(p0 is FirebaseTooManyRequestsException) {
                Log.d("FirebaseAuth", "OnVerificationFailed: FirebaseTooManyRequests: SMS Qouta")
            }
        }

        // Verification code has been sent
        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            Log.d("FirebaseAuth", "OnCodeSent:${verificationId}")

            storedVerificationId = verificationId
            forceResendToken = p1
        }

    }

    private fun signInWithPhoneAuthCredential(p0: PhoneAuthCredential) {
        mAuth.signInWithCredential(p0)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Log.d("FirebaseAuth", "signInWithCredentialSuccess")

                    // TODO - Authenticate with Server now

                    val user = it.result?.user
                    acknowledgeAuthenticationWithServer(user)

                } else {
                    Log.d("FirebaseAuth", "signInWithCredentialFailure", it.exception)
                    if(it.exception is FirebaseAuthInvalidCredentialsException) {
                        otpInput.error = "Wrong verification code! Please try again"
                    }
                }
            }
    }

    private fun acknowledgeAuthenticationWithServer(currentUser: FirebaseUser?) {
        val requestQueue = Volley.newRequestQueue(this)
        val authRequest = StringRequest(Request.Method.POST,
            getString(R.string.server_addr) + getString(R.string.route_auth),
            Response.Listener<String> {
                Log.d("ServerAuth", "OnAuthSuccess")
            },
            Response.ErrorListener {
                Log.d("ServerAuth", "OnAuthFailure: ${it.message}")

            })

        requestQueue.add(authRequest)
    }

    private fun navigateToNextPage() {
        //val intent = Intent(baseContext, VerifyAddress::class.java)
        //startActivity(intent)
    }

}


