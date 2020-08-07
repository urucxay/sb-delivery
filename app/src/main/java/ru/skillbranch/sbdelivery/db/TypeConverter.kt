package ru.skillbranch.sbdelivery.db

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun dateToLong(date: Date) = date.time

    @TypeConverter
    fun longToDate(long: Long) = Date(long)

}