package com.example.toddtodo.business.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromString(value: String): LocalDate {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun toString(value: LocalDate): String {
        return value.toString()
    }
}