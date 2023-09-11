package com.example.toddtodo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toddtodo.databinding.ActivityMainBinding
import com.example.toddtodo.utilits.APP_ACTIVITY
import com.example.toddtodo.utilits.replaceFragmentMain
import com.example.toddtodo.utilits.setStatusBarGradiantMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiantMain(this)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        replaceFragmentMain(MenuFragment())
    }
}