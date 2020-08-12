package zw.co.guava.soterio.ui.onboarding.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_verify_phone.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import zw.co.guava.soterio.R
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.entity.EntityToken
import zw.co.guava.soterio.db.repo.RepoTokens
import zw.co.guava.soterio.ui.onboarding.permissions.PrivacyPolicy
import java.util.concurrent.TimeUnit


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class VerifyPhone : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var storedVerificationId: String;
    private lateinit var forceResendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneNumber: String
    private val scope = MainScope()
    private val phoneNum = "+263785313872"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)
        mAuth = FirebaseAuth.getInstance();
        phoneNumber = intent.getStringExtra(getString(R.string.phone_number)).toString()

        val currentUser: FirebaseUser? = mAuth.currentUser;
        if(currentUser != null) {
            acknowledgeAuthenticationWithServer(currentUser)
        }

        // Disable verification button if text less than 6
        otpInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                verifyButton.isEnabled = s.length == 6 && ::storedVerificationId.isInitialized
            }
        })



        phoneNumberSubtext.text = "Enter the code sent to ${Html.fromHtml("<b>${phoneNumber}</b>")}"

        getVerificationCode(phoneNumber)


        resendCodeLink.setOnClickListener {
            getVerificationCode(phoneNumber)
        }

        editNumber.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.get_otp_change_number))
                .setMessage(getString(R.string.back_to_prev_screen))
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.change_number)) { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(this, GetOtp::class.java)
                    startActivity(intent)
                    finish()
                }
                .show()
        }

        // Start the verification process on code input
        verifyButton.setOnClickListener {
            indeterminateBar.visibility = View.VISIBLE
            activeOverlay.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE

            val credential = PhoneAuthProvider.getCredential(storedVerificationId, otpInput.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun getVerificationCode(phoneNumber: String) {
        // Control views
        indeterminateBar.visibility = View.VISIBLE
        activeOverlay.visibility = View.VISIBLE
        didntReceiveCode.visibility = View.GONE
        resendCodeLink.visibility = View.GONE
        errorMessage.visibility = View.GONE
        errorMessage.text = ""

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            authCallbacks
        )


    }

    override fun onStart() {
        super.onStart()
        didntReceiveCode.visibility = View.GONE
        resendCodeLink.visibility = View.GONE
        errorMessage.visibility = View.GONE

    }

    private val authCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            Log.d("FirebaseAuth", "OnVerificationCompleted:${p0}")
            signInWithPhoneAuthCredential(p0);
            errorMessage.visibility = View.GONE

        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("FirebaseAuth", "OnVerificationFailed: ${p0.message}")
            indeterminateBar.visibility = View.GONE
            activeOverlay.visibility = View.GONE

            if(p0 is FirebaseAuthInvalidCredentialsException) {
                Log.d("FirebaseAuth", "OnVerificationFailed: FirebaseAuthInvalidCredentials")
                errorMessage.text = "Invalid verification code"
                errorMessage.visibility = View.VISIBLE
                return
            } else if(p0 is FirebaseTooManyRequestsException) {
                Log.d("FirebaseAuth", "OnVerificationFailed: FirebaseTooManyRequests: SMS Qouta")
                errorMessage.text = "Too many authentication requests"
                errorMessage.visibility = View.VISIBLE
                return
            }

            countDown.visibility = View.GONE
            errorMessage.text = "Firebase Error: ${p0.localizedMessage}"
            errorMessage.visibility = View.VISIBLE
            resendCodeLink.visibility = View.VISIBLE
            resendCodeLink.isEnabled = true
        }

        // Verification code has been sent
        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            Log.d("FirebaseAuth", "OnCodeSent:${verificationId}")
            indeterminateBar.visibility = View.GONE
            activeOverlay.visibility = View.GONE

            // Update UI timer
            val timer = object: CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                    if(millisUntilFinished > 10000) {
                        countDown.text = "0:${millisUntilFinished/1000}"

                    } else {
                        countDown.text = "0:0${millisUntilFinished/1000}"

                    }
                }

                override fun onFinish() {
                    countDown.visibility = View.GONE
                    didntReceiveCode.visibility = View.VISIBLE
                    resendCodeLink.visibility = View.VISIBLE
                }
            }
            timer.start()

            storedVerificationId = verificationId
            forceResendToken = p1
        }

        override fun onCodeAutoRetrievalTimeOut(p0: String) {
            super.onCodeAutoRetrievalTimeOut(p0)

        }

    }

    private fun signInWithPhoneAuthCredential(p0: PhoneAuthCredential) {
        mAuth.signInWithCredential(p0)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Log.d("FirebaseAuth", "signInWithCredentialSuccess")
                    val user = it.result?.user
                    acknowledgeAuthenticationWithServer(user)

                } else {
                    Log.d("FirebaseAuth", "signInWithCredentialFailure", it.exception)
                    indeterminateBar.visibility = View.GONE
                    activeOverlay.visibility = View.GONE
                    errorMessage.visibility = View.VISIBLE

                    if(it.exception is FirebaseTooManyRequestsException) {
                        MaterialAlertDialogBuilder(this)
                            .setTitle(getString(R.string.error))
                            .setMessage(getString(R.string.server_busy))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                                super.onBackPressed()
                                dialog.dismiss()
                                finish()
                            }
                            .show()

                        return@addOnCompleteListener
                    }

                    if(it.exception is FirebaseAuthInvalidCredentialsException) {
                        errorMessage.error = "Wrong verification code! Please try again"
                        errorMessage.visibility = View.VISIBLE

                        return@addOnCompleteListener
                    }

                    MaterialAlertDialogBuilder(this)
                        .setTitle(getString(R.string.error))
                        .setMessage(getString(R.string.verification_send_error))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                            super.onBackPressed()
                            dialog.dismiss()
                            finish()
                        }
                        .show()
                }
            }
    }

    private fun acknowledgeAuthenticationWithServer(currentUser: FirebaseUser?) {
        indeterminateBar.visibility = View.VISIBLE
        activeOverlay.visibility = View.VISIBLE

        val requestQueue = Volley.newRequestQueue(this)

        // Tokens request from server
        val tokensRequest = StringRequest(Request.Method.GET,
            getString(R.string.server_addr) + getString(R.string.route_tokens) + "?uid=${mAuth.currentUser!!.uid}",
            Response.Listener {
                Log.d("ServerAccess", "OnTokenFetchSuccess")


                // Grab tokens and save them to Database
                val tokens: List<EntityToken> = Gson().fromJson(it, Array<EntityToken>::class.java).toList()
                val tokensDao = CoreDatabase.getDatabase(this).daoTokens()
                val tokensRepo = RepoTokens(tokensDao)
                scope.launch {
                    tokensRepo.saveAllTokens(tokens)
                }

                indeterminateBar.visibility = View.GONE
                activeOverlay.visibility = View.GONE

                // All set now lets navigate to next page
                navigateToNextPage()

            },
            Response.ErrorListener {
                Log.d("ServerAccess", "OnTokenFetchFailure: ${it.message}")
                indeterminateBar.visibility = View.GONE
                activeOverlay.visibility = View.GONE

            })


        // Authenticate request with server.... actually launched first before token request
        val authRequest = object: StringRequest(Request.Method.POST,
            getString(R.string.server_addr) + getString(R.string.route_auth),
            Response.Listener<String> {
                Log.d("ServerAccess", "OnAuthSuccess: $it")
                requestQueue.add(tokensRequest)
            },
            Response.ErrorListener {
                Log.d("ServerAccess", "OnAuthFailure: ${it.message}")
                indeterminateBar.visibility = View.GONE
                activeOverlay.visibility = View.GONE

                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.error))
                    .setMessage(getString(R.string.connection_error))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                        acknowledgeAuthenticationWithServer(currentUser)
                        dialog.dismiss()
                    }
                    .show()
            }){
            override fun getBodyContentType(): String {
                return "application/json"
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                val params2 = HashMap<String, String>()
                params2["uid"] = currentUser!!.uid
                params2["phoneNumber"] = phoneNumber
                return JSONObject(params2 as Map<*, *>).toString().toByteArray()
            }

        }

        requestQueue.add(authRequest)
        requestQueue.start()
    }

    private fun navigateToNextPage() {
        val intent = Intent(baseContext, PrivacyPolicy::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        indeterminateBar.visibility = View.GONE
        activeOverlay.visibility = View.GONE
        didntReceiveCode.visibility = View.GONE
        resendCodeLink.visibility = View.GONE
    }

}


