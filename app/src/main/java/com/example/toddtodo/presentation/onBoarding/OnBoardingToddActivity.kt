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
        setViewPager()
    }

    private fun setViewPager() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ThirstFragment())
        fragmentList.add(SecondFragment())

        val adapterViewPager = OnBoardingToddAdapter(
            fragmentList,
            this.supportFragmentManager,
            lifecycle
        )
        val finish = findViewById<ImageView>(R.id.finish)

        finish.setOnClickListener { v: View? ->
            val intent = Intent(
                this@OnBoardingToddActivity,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapterViewPager
        val indicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)
    }
}