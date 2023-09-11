package com.example.toddtodo.presentation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isNotEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel
import com.example.toddtodo.databinding.FragmentAddTaskScheduledBinding
import com.example.toddtodo.utilits.replaceFragmentMain
import com.example.toddtodo.viewModel.TaskViewModel

class AddTaskScheduledFragment(private val taskId: Int, private val date: String, private val position: Int) : Fragment() {
    private var _binding : FragmentAddTaskScheduledBinding? = null
    private val binding get() = _binding!!
    private lateinit var time : String
    private lateinit var viewModalTask : TaskViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddTaskScheduledBinding.inflate(inflater, container, false)

        viewModalTask = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(TaskViewModel::class.java)

        binding.btCloseAddTask.setOnClickListener { replaceFragmentMain(ScheduledFragment()) }
        binding.btAddTime.setOnClickListener { showDialogTime() }
        binding.btSave.setOnClickListener {
            saveTask()
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveTask() {
        val task = binding.etInputTask.text.toString()
        if (task.isNotEmpty() && time.isNotEmpty()){

           viewModalTask.allTask.observe(viewLifecycleOwner, Observer {
               val listTaskList = it.get(position).listTask
               val dateP = it.get(position).datePicker

               listTaskList.add(TaskList(task = task, time = time, click = false))

               viewModalTask.addNote(TaskModel(id = taskId, date = date, datePicker = dateP, listTask = listTaskList))
           })

            binding.etInputTask.text.clear()
            replaceFragmentMain(MenuFragment())
        }
    }

    private fun showDialogTime() {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.full_sreen_time)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btClose = dialog!!.findViewById<ConstraintLayout>(R.id.bt_close)
        val btDone = dialog.findViewById<ConstraintLayout>(R.id.bt_done)
        val timePicker = dialog.findViewById<TimePicker>(R.id.timePicker)

        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            time = "$hourOfDay:$minute"
        }

        btDone.setOnClickListener {
            if (timePicker.isNotEmpty()){
                dialog.cancel()
            }
        }

        btClose.setOnClickListener { dialog.cancel() }

        dialog.show()
    }
}