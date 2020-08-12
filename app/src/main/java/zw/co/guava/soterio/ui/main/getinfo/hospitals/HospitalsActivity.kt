package zw.co.guava.soterio.ui.main.getinfo.hospitals

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.guava.soterio.R
import zw.co.guava.soterio.core.classes.CentralLog


class HospitalsActivity : AppCompatActivity(){

    private lateinit var hospitalsViewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitals)


        hospitalsViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)

        val adapter = HospitalsAdapter(this)
        val rv = findViewById<RecyclerView>(R.id.hospitals_recycler_view)


        hospitalsViewModel.allWords.observe(this, Observer { hospitals ->
            // Update the cached copy of the words in the adapter.
            hospitals?.let { adapter.setHospitals(it) }
        })


        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter





    }



}