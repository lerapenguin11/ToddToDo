package com.example.toddtodo.presentation.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toddtodo.R
import com.example.toddtodo.databinding.FragmentMenuBinding
import com.example.toddtodo.databinding.FragmentThirstBinding
import com.example.toddtodo.utilits.replaceFragmentMain
import com.example.toddtodo.utilits.replaceFragmentOnBoarding

class ThirstFragment : Fragment() {
    private var _binding : FragmentThirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirstBinding.inflate(inflater, container, false)

        binding.btNext.setOnClickListener { replaceFragmentOnBoarding(SecondFragment()) }

        return binding.root
    }
}