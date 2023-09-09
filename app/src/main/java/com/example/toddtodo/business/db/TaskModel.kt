package com.example.toddtodo.business.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "date")
    val date : String,
    @Embedded
    val listTask : ArrayList<TaskList>
)

data class TaskList(
    val task : String,
    val time : String
)
