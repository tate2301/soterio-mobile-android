package zw.co.guava.soterio.ui.main.getinfo.exposure

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exposure_notification_list.view.*
import kotlinx.android.synthetic.main.item_hospitals_list.view.*
import zw.co.guava.soterio.R

class ExposureNotificationAdaptor(private val items: ArrayList<String>, private val items2: ArrayList<String>, private val items3: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_exposure_notification_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateTextView?.text = items[position]
        holder.locationTextView.text = items2[position]
        holder.regionTextView.text = items3[position]
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val dateTextView: TextView = view.exposure_date_text_view
    val locationTextView: TextView = view.exposure_notification_location_text_view
    val regionTextView: TextView = view.region_text_view
}