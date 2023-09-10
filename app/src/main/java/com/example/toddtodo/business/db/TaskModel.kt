package com.example.toddtodo.business.db

import androidx.room.*

@Entity(tableName = "task")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name ="listTask")
    @TypeConverters(TaskListConverter::class)
    var listTask: ArrayList<TaskList>
)
{
    constructor() : this(id = 0, date = "", listTask = arrayListOf())

    @JvmName("setId1")
    private fun setId(id: Int) {
        this.id = id
    }

    @JvmName("getId1")
    fun getId(): Int {
        return id
    }

    @JvmName("getDate1")
    fun getDate() : String{
        return date
    }

    @JvmName("setDate1")
    fun setDate(date: String){
        this.date = date
    }
}
