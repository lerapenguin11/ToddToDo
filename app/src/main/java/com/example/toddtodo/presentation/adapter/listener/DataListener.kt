package com.example.toddtodo.presentation.adapter.listener

import com.example.toddtodo.business.db.TaskList
import com.example.toddtodo.business.db.TaskModel

interface DataListener {

    fun getDataListener(date : TaskModel, position : Int)

    fun getDateTaskListener(date: TaskModel, task: TaskList, position: Int, positionList: Int)

    fun getDeleteTaskList(date: TaskModel, position: Int)
}