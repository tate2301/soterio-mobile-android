package zw.co.guava.soterio.ui.main.getinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main.feed.DateAdaptor

class Hospitals : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with dates
    private val hospitals: ArrayList<String> = ArrayList()
    private val distances: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitals)

        //val root = inflater.inflate(R.layout.activity_hospitals, container, false)
        // Loads dates into the ArrayList

        allNearHospitals()
        getDistances()

        // Creates a vertical Layout Manager

        val rv = findViewById<RecyclerView>(R.id.hospitals_recycler_view)
        rv.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = DateAdaptor(hospitals, distances,this)
        // Inflate the layout for this fragment

    }

    // Adds dates to the empty dates ArrayList
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