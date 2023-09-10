package com.example.toddtodo.business.db

import androidx.room.TypeConverter

interface TaskListConverterInt {

    @TypeConverter
    fun fromArrayList(value: ArrayList<TaskList>?): String?

    @TypeConverter
    fun toArrayList(value: String?): ArrayList<TaskList>?
}