package com.example.toddtodo.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList

class AllTaskAdapter() : RecyclerView.Adapter<AllTaskAdapter.AllTaskViewHolder>() {
    private val tasks = ArrayList<TaskList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_all_task_child, parent, false)

        return AllTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllTaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size

    fun setItem(newList: List<TaskList>) {
        tasks.clear()
        tasks.addAll(newList)
        notifyDataSetChanged()
    }

    inner class AllTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val taskTextView : TextView = itemView.findViewById(R.id.tv_task)
        private val timeTextView : TextView = itemView.findViewById(R.id.tv_time)

        fun bind(task: TaskList) {
            taskTextView.text = task.task
            timeTextView.text = task.time
        }
    }
}