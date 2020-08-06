package zw.co.guava.soterio.ui.onboarding.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_enable_bluetooth.*
import zw.co.guava.soterio.R

class enable_bluetooth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enable_bluetooth)

        bluetoothButton.setOnClickListener {
            val intent = Intent(baseContext, enable_notifications::class.java)
            startActivity(intent)
        }
    }
}