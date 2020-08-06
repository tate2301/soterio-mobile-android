package zw.co.guava.soterio.ui.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_enable_notifications.*
import kotlinx.android.synthetic.main.activity_get_started.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main_apk.landing_page

class get_started : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        getStartedButton.setOnClickListener {
            val intent = Intent(baseContext, landing_page::class.java)
            startActivity(intent)
        }
    }
}