package com.example.toddtodo.presentation.adapter.listener

import com.example.toddtodo.business.db.TaskList

interface TaskListener {

    fun getTaskListener(task : TaskList)
}