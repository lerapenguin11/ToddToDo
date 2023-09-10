package com.example.toddtodo.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel

class TaskAdapter(private val tasks : ArrayList<TaskList>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scheduled_child, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size

    fun setItem(newList: List<TaskList>) {
        tasks.clear()
        tasks.addAll(newList)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTextView : TextView = itemView.findViewById(R.id.tv_task)
        private val timeTextView : TextView = itemView.findViewById(R.id.tv_time)


        fun bind(task: TaskList) {
            taskTextView.text = task.task
            timeTextView.text = task.time
        }
    }
}