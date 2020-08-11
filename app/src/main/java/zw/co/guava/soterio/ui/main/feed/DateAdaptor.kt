package zw.co.guava.soterio.ui.main.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_list_item.view.*
import zw.co.guava.soterio.R

class DateAdaptor(val items: ArrayList<String>, val context: FeedFragment) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of dates in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.date_list_item, parent, false))
    }

    // Binds each date in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.dateTextView?.text = items.get(position)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each date to
    val dateTextView = view.date_text_view
}