package com.example.data.converter

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString("; ")
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split("; ").map { it.trim() }
    }
}