package zw.co.guava.soterio.ui.main.feed

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R

class FeedFragment : Fragment() {

    // Initializing an empty ArrayList to be filled with dates
    val dates: ArrayList<String> = ArrayList()


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_feed, container, false)
        // Loads dates into the ArrayList

        addDates()

        // Creates a vertical Layout Manager

        val rv = root.findViewById<RecyclerView>(R.id.date_recycler_view)
        rv.layoutManager = LinearLayoutManager(activity)

        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = DateAdaptor(dates, this)

        // Inflate the layout for this fragment

        return root

    }

    // Adds dates to the empty dates ArrayList
    fun addDates() {

        for(i in 1..21){
            dates.add("$i-12-20")
        }
    }

}