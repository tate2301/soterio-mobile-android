package zw.co.guava.soterio.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.switchmaterial.SwitchMaterial
import zw.co.guava.soterio.R
import zw.co.guava.soterio.Soterio
import zw.co.guava.soterio.ui.main.getinfo.exposure.ExposureNotificationsActivity
import zw.co.guava.soterio.ui.main.getinfo.hospitals.HospitalsActivity
import zw.co.guava.soterio.ui.main.getinfo.testing.TestingCentersActivity


class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var lottieViewSonar: LottieAnimationView;
    lateinit var toggleContactTracing: SwitchMaterial


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //lottieViewSonar = findViewById(R.id.sonarAnimationView)
        //lottieViewSonar.playAnimation()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.fragment_home, container, false)
        initRefsAndListeners(root)

        return root
    }

    private fun initRefsAndListeners(root: View) {
        val hospitals = root.findViewById<MaterialCardView>(R.id.hospitalsButton)
        val testingCentresButton  = root.findViewById<MaterialCardView>(R.id.testingCentresButton)
        val exposureNotificationsButton = root.findViewById<MaterialCardView>(R.id.exposureNotificationsButton)

        toggleContactTracing = root.findViewById(R.id.toggleContactTracing)
        toggleContactTracing.isChecked = context?.let { Soterio.isContactTracingEnabled(it) }!!

        toggleContactTracing.setOnCheckedChangeListener { _, _ ->
            val localIntent = Intent("zw.co.guava.soterio.CONTACT_TRACING_STATE_CHANGED")
            Soterio.localBroadcastManager!!.sendBroadcast(localIntent)
        }

        hospitals.setOnClickListener {
            val hospitalIntent = Intent(activity, HospitalsActivity::class.java)
            startActivity(hospitalIntent)
        }

        testingCentresButton.setOnClickListener{
            val testingCentresIntent = Intent(activity, TestingCentersActivity::class.java)
            startActivity(testingCentresIntent)
        }

        exposureNotificationsButton.setOnClickListener {
            val exposureNotificationsIntent = Intent(activity, ExposureNotificationsActivity::class.java)
            startActivity(exposureNotificationsIntent)
        }
    }

}