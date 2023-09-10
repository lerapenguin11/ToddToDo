package com.example.toddtodo.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.databinding.FragmentMenuBinding
import com.example.toddtodo.databinding.FragmentTodayBinding
import com.example.toddtodo.presentation.adapter.DateAdapter
import com.example.toddtodo.presentation.adapter.TodayAdapter
import com.example.toddtodo.utilits.replaceFragment
import com.example.toddtodo.viewModel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.util.Calendar

class TodayFragment : Fragment() {
    private var _binding : FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModal : TaskViewModel
    private val adapter = TodayAdapter()
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentDate : LocalDate = LocalDate.now()
    private var position = 0
    private var todayList = arrayListOf<TaskList>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodayBinding.inflate(inflater, container, false)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(TaskViewModel::class.java)

        binding.fabBack.setOnClickListener { replaceFragment(MenuFragment()) }

        println(currentDate)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        observeTodayTaskData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeTodayTaskData() {

        viewModal.allTask.observe(viewLifecycleOwner, Observer { list ->
            val t = list.filter { it.datePicker == currentDate }
            t.let {
                binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.adapter = adapter
                val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy")
                binding.tvDateToday.text = currentDate.format(formatter)
                adapter.setItem(it)
            }

        })

    }
}