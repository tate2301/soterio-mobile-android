package zw.co.guava.soterio.ui.main.getinfo.hospitals

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hospitals_list.view.*
import zw.co.guava.soterio.R

class DateAdaptor(private val items: ArrayList<String>, private val items2: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of dates in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hospitals_list, parent, false)
        )
    }

    // Binds each date in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.hospitalsTextView?.text = items[position]
        holder.hospitalsDistanceTextView.text = items2[position]
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each date to
    val hospitalsTextView: TextView = view.hospitals_name_text_view
    val hospitalsDistanceTextView: TextView = view.hospitals_distances_text_view
}