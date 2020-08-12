package zw.co.guava.soterio.ui.main.getinfo.hospitals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R

class Hospitals : AppCompatActivity() {

    private val hospitals: ArrayList<String> = ArrayList()
    private val distances: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitals)

        allNearHospitals()
        getDistances()

        val rv = findViewById<RecyclerView>(R.id.hospitals_recycler_view)
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = DateAdaptor(
            hospitals,
            distances,
            this
        )

    }

    private fun allNearHospitals() {
        hospitals.add("Chemagamba Hospital")
        hospitals.add("Chinhoyi Hospital")
        hospitals.add("Ruvimbo Phase 2 Clinic")
        hospitals.add("Rujeko Hospital")
    }

    private fun getDistances() {
        distances.add("12.3km")
        distances.add("13.9km")
        distances.add("10.1km")
        distances.add("11.6km")
    }
}