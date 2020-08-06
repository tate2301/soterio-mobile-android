package zw.co.guava.soterio.ui.main_apk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import zw.co.guava.soterio.R

class MainFragment : AppCompatActivity() {

    lateinit var lottieViewSonar: LottieAnimationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        lottieViewSonar = findViewById(R.id.sonarAnimationView)
        lottieViewSonar.playAnimation()
    }
}