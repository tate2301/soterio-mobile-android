package zw.co.guava.soterio.ui.main.feed

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_feed.*
import zw.co.guava.soterio.R

class FeedFragment : Fragment() {

    // Initializing an empty ArrayList to be filled with animals
    val dates: ArrayList<String> = ArrayList()


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_feed, container, false)
        // Loads animals into the ArrayList

        addAnimals()

        // Creates a vertical Layout Manager

        val rv = root.findViewById<RecyclerView>(R.id.rv_animal_list)
        rv.layoutManager = LinearLayoutManager(activity)

        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = DateAdaptor(dates, this)

        // Inflate the layout for this fragment


        return root

    }

    // Adds animals to the empty animals ArrayList
    fun addAnimals() {

        for(i in 1..21){
            dates.add("$i-12-20")
        }
    }

}