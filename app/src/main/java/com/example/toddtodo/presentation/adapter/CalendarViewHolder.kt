package com.example.toddtodo.presentation.adapter

import android.R
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.presentation.adapter.listener.CalendarListener


class CalendarViewHolder(itemView: View, onItemListener: CalendarListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val dayOfMonth: TextView
    private val onItemListener: CalendarListener

    init {
        dayOfMonth = itemView.findViewById(com.example.toddtodo.R.id.tv_day_calendar)
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        onItemListener.getCalendar(adapterPosition, dayOfMonth.text as String)
    }
}