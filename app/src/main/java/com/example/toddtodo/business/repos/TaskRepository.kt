package com.example.toddtodo.business.repos

import androidx.lifecycle.LiveData
import com.example.toddtodo.business.db.TaskDao
import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel

class TaskRepository(private val taskDao: TaskDao) {

    val allNotes: LiveData<List<TaskModel>> = taskDao.getAllNotes()

    suspend fun insert(task: TaskModel) {
        taskDao.insert(task)
    }

    suspend fun delete(task: TaskModel){
        taskDao.delete(task)
    }

    suspend fun update(task: TaskModel){
        taskDao.update(task)
    }

    fun updateTask(task : TaskList){
        taskDao.updateSubTasks(task)
    }

    fun deleteTask(taskModel: TaskList){
        taskDao.deleteSubTask(taskModel)
    }
}