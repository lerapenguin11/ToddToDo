package com.example.toddtodo.presentation.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.toddtodo.R
import com.example.toddtodo.presentation.MainActivity
import com.example.toddtodo.utilits.APP_ACTIVITY_ONBOARDING
import com.example.toddtodo.utilits.setStatusBarGradiantOnBoarding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardingToddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_todd)
        APP_ACTIVITY_ONBOARDING = this
        setStatusBarGradiantOnBoarding(this)
        setViewPagerTodd()
    }

    private fun setViewPagerTodd() {
        val fragList = ArrayList<Fragment>()
        fragList.add(ThirstFragment())
        fragList.add(SecondFragment())

        val adapterViewPager = OnBoardingToddAdapter(
            fragList,
            this.supportFragmentManager,
            lifecycle
        )
        val close = findViewById<ImageView>(R.id.finish)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapterViewPager
        val indicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)

        close.setOnClickListener { v: View? ->
            val intent = Intent(
                this@OnBoardingToddActivity,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}