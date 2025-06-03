package com.example.data.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.converter.ListStringConverter
import com.example.domain.model.vacancy.Address
import com.example.domain.model.vacancy.Experience
import com.example.domain.model.vacancy.Salary

@Entity(tableName = "favorite_vacancies")
@TypeConverters(ListStringConverter::class)
data class VacancyDbo(
    @PrimaryKey val id: String,
    val lookingNumber: Int,
    val title: String,
    @Embedded val address: Address,
    val company: String,
    @Embedded val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    @Embedded val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)
