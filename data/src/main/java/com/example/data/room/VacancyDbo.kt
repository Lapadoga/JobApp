package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.vacancy.Address
import com.example.domain.model.vacancy.Experience
import com.example.domain.model.vacancy.Salary

@Entity(tableName = "favorite_vacancies")
data class VacancyDbo(
    @PrimaryKey val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)
