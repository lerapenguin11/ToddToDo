package com.example.toddtodo.business.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import java.time.LocalDate

@Entity(tableName = "task")
data class TaskModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name ="listTask")
    @TypeConverters(TaskListConverter::class)
    var listTask: ArrayList<TaskList>,
    @ColumnInfo(name = "datePicker")
    @TypeConverters
    var datePicker: LocalDate?
)
{
    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(id = 0, date = "", listTask = arrayListOf(), datePicker = null)

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

    @JvmName("setDatePicker1")
    fun setDatePicker(datePicker : LocalDate){
        this.datePicker = datePicker
    }

    @JvmName("getDatePicker1")
    fun getDatePicker() : LocalDate{
        return datePicker!!
    }
}
