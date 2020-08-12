package zw.co.guava.soterio.ui.main.getinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hospitals_list.view.*
import zw.co.guava.soterio.R

class TestingCentersAdaptor(private val items: ArrayList<String>, private val items2: ArrayList<String>, val context: Context) : RecyclerView.Adapter<TestingViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestingViewHolder {
        return TestingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_testing_centers_list, parent, false))
    }

    override fun onBindViewHolder(holder: TestingViewHolder, position: Int) {
        holder.testingCentersTextView.text = items[position]
        holder.testingCentersDistanceTextView.text = items2[position]
    }
}

class TestingViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val testingCentersTextView: TextView = view.hospitals_name_text_view
    val testingCentersDistanceTextView: TextView = view.hospitals_distances_text_view
}