package zw.co.guava.soterio.ui.main.getinfo.exposure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R

class ExposureNotifications : AppCompatActivity() {

    private val dates: ArrayList<String> = ArrayList()
    private val location: ArrayList<String> = ArrayList()
    private val region: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exposure_notifications)

        getDates()
        getLocation()
        getRegion()

        val rv = findViewById<RecyclerView>(R.id.exposure_notifications_recycler_view)
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = ExposureNotificationAdaptor(
            dates,
            location,
            region,
            this
        )

    }

    private fun getDates() {
        dates.add("Thu, Jul 22 - 14:22")
        dates.add("Thu, Jul 22 - 16:34")
        dates.add("Fri, Jul 23 - 10:10")
        dates.add("Fri, Jul 23 - 11:12")
        dates.add("Fri, Jul 23 - 11:54")
        dates.add("Fri, Jul 23 - 13:22")
    }

    private fun getLocation() {
        location.add("Ruvimbo Phase 2")
        location.add("Chemagamba road")
        location.add("Mzari park")
        location.add("Chinhoyi University of Technology Gate")
        location.add("Chinhoyi High School")
        location.add("Chinhoyi Police Station")
    }

    private fun getRegion() {
        region.add("Chinhoyi, Mashonaland West")
        region.add("Chinhoyi, Mashonaland West")
        region.add("Unknown, Co-ordinates unavailable")
        region.add("Chinhoyi, Mashonaland West")
        region.add("Chinhoyi, Mashonaland West")
        region.add("Chinhoyi, Mashonaland West")
    }
}