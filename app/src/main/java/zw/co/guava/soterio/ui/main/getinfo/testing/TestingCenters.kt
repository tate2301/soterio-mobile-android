package zw.co.guava.soterio.ui.main.getinfo.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main.getinfo.hospitals.HospitalsAdapter

class TestingCenters : AppCompatActivity() {

    private val testingCenters: ArrayList<String> = ArrayList()
    private val distances: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_centers)


        val rv = findViewById<RecyclerView>(R.id.testing_centers_recycler_view)
        rv.layoutManager = LinearLayoutManager(this)



    }
}