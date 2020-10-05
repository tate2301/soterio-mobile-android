package zw.co.guava.soterio.ui.main.getinfo.hospitals

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.item_hospitals_list.view.*
import zw.ac.cut.soterio.sble.features.CentralLog
import zw.co.guava.soterio.R
import zw.co.guava.soterio.db.entity.EntityHospital


class HospitalsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HospitalsAdapter.HospitalViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var hospitals = emptyList<EntityHospital>() // Cached copy of words


    inner class HospitalViewHolder (view: View ) : RecyclerView.ViewHolder(view){
        val hospitalsTextView: TextView = view.hospitals_name_text_view
        val hospitalsDistanceTextView: TextView = view.hospitals_distances_text_view
        val hospitalContainer: MaterialCardView = view.hospitalContainer

    }


    // Gets the number of dates in the list
    override fun getItemCount(): Int {
        return hospitals.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {

        return HospitalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hospitals_list, parent, false))
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.hospitalsTextView.text = hospitals[position].name
        holder.hospitalsDistanceTextView.text = hospitals[position].address

        // Set OnClickListener
        holder.hospitalContainer.setOnClickListener {
                CentralLog.d("Adapter", "Item Clicked")

                val dialog = BottomSheetDialog(it.context)
                val hospital = hospitals[position]
                val view = inflater.inflate(R.layout.dialog_get_directions, null)

                view.findViewById<TextView>(R.id.name).text = hospital.name
                view.findViewById<TextView>(R.id.distanceText).text = hospital.address
                view.findViewById<MaterialButton>(R.id.getDirectionsBtn).setOnClickListener {
                    dialog.dismiss()
                }

                // Send to Google Maps for directions
                view.findViewById<MaterialButton>(R.id.getDirectionsBtn).setOnClickListener {
                    val uri = "http://maps.google.com/maps?daddr=" + hospital.latitude.toString() + "," + hospital.longitude.toString()
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    view.context.startActivity(intent)
                }

                dialog.window?.setLayout (LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                dialog.window?.setGravity (Gravity.BOTTOM);

                dialog.setContentView(view)
                dialog.show()
        }
    }


    internal fun setHospitals(words: List<EntityHospital>) {
        this.hospitals = words
        notifyDataSetChanged()
    }


}

