package zw.co.guava.soterio.ui.main_apk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.onboarding.Onboarding
import zw.co.guava.soterio.ui.onboarding.permissions.privacy_policy

class MainActivity : AppCompatActivity() {

    lateinit var lottieViewSonar: LottieAnimationView;
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        mAuth = FirebaseAuth.getInstance()
        lottieViewSonar = findViewById(R.id.sonarAnimationView)
        lottieViewSonar.playAnimation()
    }

    override fun onStart() {
        super.onStart()
        if(mAuth.currentUser == null){
            val intent = Intent(baseContext, Onboarding::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}