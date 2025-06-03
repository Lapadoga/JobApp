package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VacancyDbo::class], version = 1)
abstract class JobDatabase : RoomDatabase() {
    abstract fun getVacanciesDao(): VacancyDao
}