package com.example.toddtodo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toddtodo.R
import com.example.toddtodo.databinding.FragmentAllTaskBinding
import com.example.toddtodo.databinding.FragmentMenuBinding
import com.example.toddtodo.presentation.adapter.AllDateAdapter
import com.example.toddtodo.presentation.adapter.DateAdapter
import com.example.toddtodo.utilits.replaceFragment
import com.example.toddtodo.viewModel.TaskViewModel

class AllTaskFragment : Fragment() {
    private var _binding : FragmentAllTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModal : TaskViewModel
    private val adapter = AllDateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAllTaskBinding.inflate(inflater, container, false)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(TaskViewModel::class.java)

        observeAllTaskData()

        binding.fabBack.setOnClickListener { replaceFragment(MenuFragment()) }

        return binding.root
    }

    private fun observeAllTaskData() {
        binding.rvAllTask.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvAllTask.adapter = adapter

        viewModal.allTask.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.setItem(it)
            }
        })
    }

}