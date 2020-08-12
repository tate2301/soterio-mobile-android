package zw.co.guava.soterio.ui.main.getinfo.testing

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import zw.co.guava.soterio.R


class TestingCentersActivity : AppCompatActivity() {

    private lateinit var testingCentreViewModel: TestingCentreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_centers)

        val mToolbar: MaterialToolbar = findViewById<View>(R.id.toolbar) as MaterialToolbar
        mToolbar.title = "Testing Centres"
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        testingCentreViewModel = ViewModelProvider(this).get(TestingCentreViewModel::class.java)

        val adapter = TestingCentresAdapter(this)
        testingCentreViewModel.allTestingCentres.observe(this, Observer { testingCentres ->
            // Update the cached copy of the words in the adapter.
            testingCentres?.let { adapter.setTestingCentres(it) }
        })
        val rv = findViewById<RecyclerView>(R.id.testing_centers_recycler_view)
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = adapter


    }

}