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
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel
import com.example.toddtodo.databinding.FragmentScheduledBinding
import com.example.toddtodo.presentation.adapter.DateAdapter
import com.example.toddtodo.presentation.adapter.listener.DataListener
import com.example.toddtodo.utilits.replaceFragment
import com.example.toddtodo.viewModel.TaskViewModel

class ScheduledFragment : Fragment(), DataListener {
    private var _binding : FragmentScheduledBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModal : TaskViewModel
    private val adapter = DateAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScheduledBinding.inflate(inflater, container, false)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(TaskViewModel::class.java)

        observeTaskData()

        binding.fabBack.setOnClickListener { replaceFragment(MenuFragment()) }

        return binding.root
    }

    private fun observeTaskData() {
        binding.rvScheduled.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvScheduled.adapter = adapter

        viewModal.allTask.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.setItem(it)
            }
        })
    }

    override fun getDataListener(date: TaskModel, position : Int) {
        replaceFragment(AddTaskScheduledFragment(date.id, date.date, position))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDateTaskListener(date: TaskModel, task: TaskList, position: Int, positionList: Int) {
        viewModal.allTask.observe(viewLifecycleOwner, Observer {
            val listTaskList = it.get(positionList).listTask
            var p = 0

            for (i in listTaskList){
                if (i.id == task.id){
                    listTaskList.get(position).click = true
                }
                p++
            }

            viewModal.updateNote(
                TaskModel(id = date.id, date = date.date, datePicker = date.datePicker, listTask = listTaskList))
            return@Observer
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDeleteTaskList(date: TaskModel, position: Int) {
        viewModal.allTask.observe(viewLifecycleOwner, Observer {
            val listTaskList = it.get(position).listTask
            val checkList = listTaskList.filter { it.click }
            if (checkList.size == listTaskList.size){
                viewModal.deleteNote(TaskModel(date.id, date.date,
                    datePicker = date.datePicker, listTask = listTaskList))
            }
        })
    }
}