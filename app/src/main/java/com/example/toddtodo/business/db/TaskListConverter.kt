package com.example.toddtodo.business.db

import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONObject

class TaskListConverter {
    @TypeConverter
    fun fromArrayList(value: ArrayList<TaskList>?): String? {
        return value?.let { taskList ->
            JSONArray(taskList.map { task ->
                JSONObject().apply {
                    put("task", task.task)
                    put("time", task.time)
                }
            }).toString()
        }
    }

    @TypeConverter
    fun toArrayList(value: String?): ArrayList<TaskList>? {
        return value?.let { jsonString ->
            val jsonArray = JSONArray(jsonString)
            val taskList = ArrayList<TaskList>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val task = jsonObject.getString("task")
                val time = jsonObject.getString("time")
                taskList.add(TaskList(task, time))
            }
            taskList
        }
    }
}