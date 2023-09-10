package com.example.toddtodo.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.toddtodo.R
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel

class TodayAdapter() : RecyclerView.Adapter<TodayAdapter.TodayViewHolder>() {
    private val today = mutableListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scheduled_child, parent, false)

        return TodayViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodayViewHolder, position: Int) {
        val dateItem = today[position]
        holder.bind(dateItem)
    }

    override fun getItemCount(): Int = today.size

    fun setItem(newList: List<TaskModel>) {
        today.clear()
        today.addAll(newList)
        notifyDataSetChanged()
    }

    inner class TodayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val task : TextView = itemView.findViewById(R.id.tv_task)
        private val time : TextView = itemView.findViewById(R.id.tv_time)
        private val box : ConstraintLayout = itemView.findViewById(R.id.box_task)

        fun bind(todayItem: TaskModel) {
            for (i in todayItem.listTask){
                task.text = i.task
                time.text = i.time
            }
        }
    }
}