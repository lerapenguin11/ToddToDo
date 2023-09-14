package com.example.toddtodo.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel
import com.example.toddtodo.presentation.adapter.listener.DataListener

class DateAdapter(private val listener : DataListener) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
    private val dates = mutableListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scheduled_parent, parent, false)

        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val dateItem = dates[position]
        holder.bind(dateItem)
        holder.btDelete.setOnClickListener {
            listener.getDeleteTaskList(dateItem, position = position)
        }

        holder.taskRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        val adapter = TaskAdapter(object : TaskAdapter.RecyclerView2ClickListener {

            override fun onItemClick(position_: Int, task: TaskList) {
                listener.getDateTaskListener(dateItem, task, position_, positionList = position)
            }
        })
        holder.taskRecyclerView.adapter = adapter
        adapter.setItem(dateItem.listTask)

        holder.newItemTask.setOnClickListener {
            listener.getDataListener(dateItem, position = position)
        }
    }

    override fun getItemCount(): Int = dates.size

    fun setItem(newList: List<TaskModel>) {
        dates.clear()
        dates.addAll(newList)
        notifyDataSetChanged()
    }

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.tv_date)
        val taskRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
        val newItemTask: ConstraintLayout =
            itemView.findViewById(R.id.cl_create_new_item_child)
        val btDelete : ConstraintLayout = itemView.findViewById(R.id.cl_delete_item_parent)

        fun bind(dateItem: TaskModel) {
            dateTextView.text = dateItem.date
        }
    }
}