package zw.co.guava.soterio.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import zw.co.guava.soterio.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var lottieViewSonar: LottieAnimationView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //lottieViewSonar = findViewById(R.id.sonarAnimationView)
        //lottieViewSonar.playAnimation()

    }

}