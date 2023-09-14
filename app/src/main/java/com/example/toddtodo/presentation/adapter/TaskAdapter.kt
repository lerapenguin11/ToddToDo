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

class TaskAdapter(/*private val tasks : ArrayList<TaskList>*/private val itemClickListener: RecyclerView2ClickListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var onFavoriteClickListener: ((TaskList) -> Unit)? = null
    private val tasks = ArrayList<TaskList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scheduled_child, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.bind(task)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position, task)
            if (task.click){
                holder.taskTextView.paintFlags = holder.taskTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.itemClick.setBackgroundResource(R.drawable.bg_click_item)
            }
        }

        if (task.click){
            holder.taskTextView.paintFlags = holder.taskTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.itemClick.setBackgroundResource(R.drawable.bg_click_item)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun setItem(newList: List<TaskList>) {
        tasks.clear()
        tasks.addAll(newList)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val taskTextView : TextView = itemView.findViewById(R.id.tv_task)
        private val timeTextView : TextView = itemView.findViewById(R.id.tv_time)
        val itemClick : ConstraintLayout = itemView.findViewById(R.id.box_task)
        val item : ConstraintLayout = itemView.findViewById(R.id.item_view)

        fun bind(task: TaskList) {
            taskTextView.text = task.task
            timeTextView.text = task.time

            itemView.setOnClickListener {
                //onFavoriteClickListener?.invoke(task)
            }
        }
    }

    interface RecyclerView2ClickListener {
        fun onItemClick(position_: Int, task : TaskList)
    }

    fun setOnFavoriteClickListener(listener: (TaskList) -> Unit) {
        onFavoriteClickListener = listener
    }
}