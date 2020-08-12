package zw.co.guava.soterio.ui.main.getinfo.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R

class TestingCenters : AppCompatActivity() {

    private val testingCenters: ArrayList<String> = ArrayList()
    private val distances: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_centers)

        allNearTestingCenters()
        getDistances()

        val rv = findViewById<RecyclerView>(R.id.testing_centers_recycler_view)
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = TestingCentersAdaptor(
            testingCenters,
            distances,
            this
        )

    }

    // Adds dates to the empty dates ArrayList
    private fun allNearTestingCenters() {
        testingCenters.add("Chemagamba Testing Center")
        testingCenters.add("Chinhoyi Testing Center")
        testingCenters.add("Ruvimbo Phase 2 Testing Center")
        testingCenters.add("Rujeko Testing Center")
    }

    private fun getDistances() {
        distances.add("12.3km")
        distances.add("13.9km")
        distances.add("10.1km")
        distances.add("11.6km")
    }
}