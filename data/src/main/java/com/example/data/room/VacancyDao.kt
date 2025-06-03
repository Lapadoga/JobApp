package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vacancy: VacancyDbo)

    @Delete
    suspend fun remove(vacancy: VacancyDbo)

    @Query("select * from favorite_vacancies")
    suspend fun getAll(): List<VacancyDbo>
}