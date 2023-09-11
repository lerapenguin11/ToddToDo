package com.example.toddtodo.presentation.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toddtodo.R
import com.example.toddtodo.databinding.FragmentSecondBinding
import com.example.toddtodo.databinding.FragmentThirstBinding
import com.example.toddtodo.presentation.MainActivity
import com.example.toddtodo.utilits.replaceFragmentOnBoarding

class SecondFragment : Fragment() {
    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.btStart.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}