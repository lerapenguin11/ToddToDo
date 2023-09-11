package com.example.toddtodo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toddtodo.databinding.FragmentMenuBinding
import com.example.toddtodo.utilits.replaceFragmentMain

class MenuFragment : Fragment() {
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        onClick()
    }

    private fun onClick() {
        binding.blockScheduled.setOnClickListener {
            replaceFragmentMain(ScheduledFragment())
        }

        binding.fabAddTask.setOnClickListener{ replaceFragmentMain(AddTaskFragment()) }

        binding.blockToday.setOnClickListener { replaceFragmentMain(TodayFragment()) }

        binding.blockAllTasks.setOnClickListener { replaceFragmentMain(AllTaskFragment()) }
    }
}