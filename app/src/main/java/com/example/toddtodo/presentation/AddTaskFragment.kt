package com.example.toddtodo.presentation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel
import com.example.toddtodo.databinding.FragmentAddTaskBinding
import com.example.toddtodo.presentation.adapter.CalendarAdapter
import com.example.toddtodo.presentation.adapter.listener.CalendarListener
import com.example.toddtodo.utilits.replaceFragmentMain
import com.example.toddtodo.viewModel.TaskViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class AddTaskFragment : Fragment(), CalendarListener {
    private var _binding : FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: LocalDate? = null
    private lateinit var month : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var date : String
    private lateinit var time : String
    private lateinit var viewModal : TaskViewModel
    private lateinit var datePicker : LocalDate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(TaskViewModel::class.java)


        binding.btCloseAddTask.setOnClickListener { replaceFragmentMain(MenuFragment()) }

        binding.btAddDate.setOnClickListener { showDialogDate() }
        binding.btAddTime.setOnClickListener { showDialogTime() }
        binding.btSave.setOnClickListener {
            saveTask()
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveTask() {
        val task = binding.etInputTask.text.toString()
        if (task.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()){
            viewModal.addNote(TaskModel(/*id = taskId.getId(),*/ date = date,
                listTask = arrayListOf(TaskList(task = task, time = time, click = false)), datePicker = datePicker
            ))

            /*viewModal.updateNote(TaskModel(id = 0, date = date, listTask = arrayListOf()))*/

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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDialogDate() {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.full_screen_calendar)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        month = dialog!!.findViewById(R.id.tv_month)
        recyclerView = dialog.findViewById(R.id.rv_calendar)
        selectedDate = LocalDate.now();
        setMonthView();
        val btBack = dialog.findViewById<ImageView>(R.id.ic_back_calendar)
        val btForward = dialog.findViewById<ImageView>(R.id.ic_forward_calendar)
        val btClose = dialog.findViewById<ConstraintLayout>(R.id.bt_close)
        val btDone = dialog.findViewById<ConstraintLayout>(R.id.bt_done)

        btBack.setOnClickListener { previousMonthAction() }
        btForward.setOnClickListener { nextMonthAction() }
        dialog.show()

        btClose.setOnClickListener { dialog.cancel() }
        btDone.setOnClickListener {
            if (date.isNotEmpty()){
                dialog.cancel()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        month.text = monthYearFromDate(selectedDate!!)
        val daysInMonth = daysInMonthArray(selectedDate!!)
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireActivity().getApplicationContext(), 7)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction() {
        selectedDate = selectedDate!!.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction() {
        selectedDate = selectedDate!!.plusMonths(1)
        setMonthView()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getCalendar(position: Int, day: String) {
        if (!day.equals("")) {
            val message = "Selected Date $day " + monthYearFromDate(
                selectedDate!!
            )
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
        datePicker = selectedDate!!
        date = "$day " + monthYearFromDate(selectedDate!!)
    }
}