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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.databinding.FragmentAddTaskBinding
import com.example.toddtodo.presentation.adapter.CalendarAdapter
import com.example.toddtodo.presentation.adapter.listener.CalendarListener
import com.example.toddtodo.utilits.replaceFragment
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class AddTaskFragment : Fragment(), CalendarListener {
    private var _binding : FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: LocalDate? = null
    private lateinit var month : TextView
    private lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        binding.btCloseAddTask.setOnClickListener { replaceFragment(MenuFragment()) }

        binding.btAddDate.setOnClickListener { showDialog() }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDialog() {
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

        btBack.setOnClickListener { previousMonthAction() }
        btForward.setOnClickListener { nextMonthAction() }

        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        month.setText(monthYearFromDate(selectedDate!!))
        val daysInMonth = daysInMonthArray(selectedDate!!)
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireActivity().getApplicationContext(), 7)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(calendarAdapter)
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
    }
}