package zw.co.guava.soterio.ui.main.getinfo.testing

import android.app.ActionBar
import android.content.Context
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
import kotlinx.android.synthetic.main.item_testing_center_list.view.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.db.entity.EntityTestingCentre

class TestingCentresAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TestingCentresAdapter.TestingCentreViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var testingCentres = emptyList<EntityTestingCentre>() // Cached copy of words


    inner class TestingCentreViewHolder (view: View ) : RecyclerView.ViewHolder(view){
        val testingCentresTextView: TextView = view.testing_center_name_text_view
        val testingCentresDistanceTextView: TextView = view.testing_center_distances_text_view
        val testingCentreContainer: MaterialCardView = view.testing_centre_container

    }


    private fun calculateDistanceFromMe(): Int {
        return 1
    }

    // Gets the number of dates in the list
    override fun getItemCount(): Int {
        return testingCentres.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestingCentreViewHolder {

        return TestingCentreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_testing_center_list, parent, false))
    }

    // Binds each date in the ArrayList to a view
    override fun onBindViewHolder(holder: TestingCentreViewHolder, position: Int) {
        holder.testingCentresTextView.text = testingCentres[position].name
        holder.testingCentresDistanceTextView.text = "${calculateDistanceFromMe()} km"

        holder.testingCentreContainer.setOnClickListener {
                CentralLog.d("Adapter", "Item Clicked")

                val dialog = BottomSheetDialog(it.context)
                val testingCentre = testingCentres[position]
                val view = inflater.inflate(R.layout.dialog_get_directions, null)

                view.findViewById<TextView>(R.id.name).text = testingCentre.name
                view.findViewById<TextView>(R.id.distanceText).text = "Approximately ${calculateDistanceFromMe()} km from your location"
                view.findViewById<MaterialButton>(R.id.getDirectionsBtn).setOnClickListener {
                    dialog.dismiss()
                }

                dialog.window?.setLayout (LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                dialog.window?.setGravity (Gravity.BOTTOM);

                dialog.setContentView(view)
                dialog.show()
        }
    }


    internal fun setTestingCentres(testingCentres: List<EntityTestingCentre>) {
        this.testingCentres = testingCentres
        notifyDataSetChanged()
    }

    internal fun getPosition(): Int {
        return getPosition()
    }


}

