package zw.co.guava.soterio.ui.main.getinfo.testing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hospitals_list.view.*
import kotlinx.android.synthetic.main.item_testing_center_list.view.*
import zw.co.guava.soterio.R

class TestingCentersAdaptor(private val items: ArrayList<String>, private val items2: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_testing_center_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.testingCenterNameTextView?.text = items[position]
        holder.testingCenterDistanceTextView.text = items2[position]
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val testingCenterNameTextView: TextView = view.testing_center_name_text_view
    val testingCenterDistanceTextView: TextView = view.testing_center_distances_text_view
}