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
    private val dates: ArrayList<String> = ArrayList()


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_feed, container, false)

        val rv = root.findViewById<RecyclerView>(R.id.rv_parent)
        rv.layoutManager = LinearLayoutManager(activity)

        rv.adapter = ParentAdapter(ParentDataFactory
            .getParents(4))

        return root
    }
}