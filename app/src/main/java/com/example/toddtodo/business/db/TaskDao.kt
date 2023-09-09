package com.example.toddtodo.business.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task : TaskModel)

    @Delete
    fun delete(task : TaskModel)

    @Query("Select * from task order by id ASC")
    fun getAllNotes(): LiveData<List<TaskModel>>

    @Update
    fun update(task: TaskModel)
}