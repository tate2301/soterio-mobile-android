package zw.co.guava.soterio.ui

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_onboarding.*
import zw.co.guava.soterio.R

class Onboarding : AppCompatActivity() {

    lateinit var mSlideViewPager: ViewPager
    lateinit var mDots: Array<TextView>
    lateinit var mDotLayout: LinearLayout

    private lateinit var sliderAdapter: SliderAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        mSlideViewPager = findViewById<ViewPager>(R.id.slideViewPager)
        mDotLayout = findViewById<LinearLayout>(R.id.dotsLayout)
        sliderAdapter = SliderAdapter(this)


        mSlideViewPager.adapter = sliderAdapter

        val getStartedBtn = findViewById<Button>(R.id.getStartedBtn)

        getStartedBtn.setOnClickListener {
            val intent = Intent(baseContext, Onboarding::class.java)
            startActivity(intent)

        }

        addDotsIndicator(0)

        mSlideViewPager.addOnPageChangeListener(viewListener)
        
    }


    //  Onboarding dots controller
    @RequiresApi(Build.VERSION_CODES.M)
    fun addDotsIndicator(positin: Int): Unit{

        mDots = arrayOf(TextView(this), TextView(this), TextView(this))
        mDotLayout.removeAllViews()

        // Instantiate dots
        for(i in mDots.indices step 1){
            mDots[i] = TextView(this)
            mDots[i].text = Html.fromHtml("&#8226")
            mDots[i].textSize = 50F
            mDots[i].setTextColor(getColor(R.color.dotBaseColor))


            mDotLayout.addView(mDots[i])
        }

        //  Change dot color
        if(mDots.isNotEmpty()){
            mDots[positin].setTextColor(getColor(R.color.colorPrimary))
        }
    }


    private var viewListener: ViewPager.OnPageChangeListener = object: ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onPageSelected(position: Int) {
            addDotsIndicator(position)
        }

    }

}