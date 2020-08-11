package zw.co.guava.soterio.ui.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_exposure_notifications.*
import kotlinx.android.synthetic.main.fragment_home.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main.services.ExposureNotifications
import zw.co.guava.soterio.ui.onboarding.auth.GetOtp

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var lottieViewSonar: LottieAnimationView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //lottieViewSonar = findViewById(R.id.sonarAnimationView)
        //lottieViewSonar.playAnimation()
    }
}