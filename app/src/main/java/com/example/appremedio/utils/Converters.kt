package com.example.appremedio.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun stringDateToLong(data: String): Long? {
        val currentLocale = Locale.getDefault()
        val formatData = SimpleDateFormat("dd/MM/yyyy - HH:mm", currentLocale)
        val dataNasc = formatData.parse(data)
        return dateToTimestamp(dataNasc)
    }

    @TypeConverter
    fun longToStringDate(data: Long?): String {
        if (data == null){
            return ""
        }
        val dataConverter = fromTimestamp(data)
        val currentLocale = Locale.getDefault()
        val formatData = SimpleDateFormat("dd/MM/yyyy - HH:mm", currentLocale)
        return formatData.format(dataConverter)
    }
}