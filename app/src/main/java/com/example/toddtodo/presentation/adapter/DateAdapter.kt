package com.example.toddtodo.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskModel

class DateAdapter() : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
    private val dates = mutableListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scheduled_parent, parent, false)

        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val dateItem = dates[position]
        holder.bind(dateItem)
    }

    override fun getItemCount(): Int = dates.size

    fun setItem(newList: List<TaskModel>) {
        dates.clear()
        dates.addAll(newList)
        notifyDataSetChanged()
    }

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView : TextView = itemView.findViewById(R.id.tv_date)
        private val taskRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)

        fun bind(dateItem: TaskModel) {
            dateTextView.text = dateItem.date
            taskRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            taskRecyclerView.adapter = TaskAdapter(dateItem.listTask)
        }
    }
}