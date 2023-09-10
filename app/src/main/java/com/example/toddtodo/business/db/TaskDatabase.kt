package com.example.toddtodo.business.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(TaskModel::class), version = 5, exportSchema = false)
@TypeConverters(TaskListConverter::class)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun getTaskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database_5"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}