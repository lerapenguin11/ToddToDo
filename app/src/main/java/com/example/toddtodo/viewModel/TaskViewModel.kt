package com.example.toddtodo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.toddtodo.business.db.TaskDatabase
import com.example.toddtodo.business.db.TaskModel
import com.example.toddtodo.business.repos.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val allTask : LiveData<List<TaskModel>>
    val repository : TaskRepository

    init {
        val dao = TaskDatabase.getDatabase(application).getTaskDao()
        repository = TaskRepository(dao)
        allTask = repository.allNotes
    }

    fun deleteNote (task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun updateNote(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }

    fun addNote(task: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }
}