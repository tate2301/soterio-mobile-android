package zw.co.guava.soterio.ui.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_enable_bluetooth.*
import kotlinx.android.synthetic.main.activity_enable_notifications.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.auth.VerifyAddress

class enable_notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enable_notifications)

        notificationButton.setOnClickListener {
            val intent = Intent(baseContext, get_started::class.java)
            startActivity(intent)
        }
    }
}