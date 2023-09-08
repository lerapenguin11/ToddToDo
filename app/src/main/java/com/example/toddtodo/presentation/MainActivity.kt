package com.example.toddtodo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toddtodo.R
import com.example.toddtodo.databinding.ActivityMainBinding
import com.example.toddtodo.utilits.APP_ACTIVITY
import com.example.toddtodo.utilits.replaceFragment
import com.example.toddtodo.utilits.setStatusBarGradiant

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiant(this)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        replaceFragment(MenuFragment())
    }
}