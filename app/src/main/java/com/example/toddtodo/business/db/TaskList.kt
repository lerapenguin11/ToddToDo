package com.example.toddtodo.business.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity()
data class TaskList(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @Ignore
    val task : String,
    @Ignore
    val time : String,
    @Ignore
    var click : Boolean
)