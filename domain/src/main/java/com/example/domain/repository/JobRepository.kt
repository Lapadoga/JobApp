package com.example.domain.repository

import com.example.domain.ApiAnswer
import com.example.domain.model.vacancy.Vacancy

interface JobRepository {
    suspend fun getData(): ApiAnswer?
    suspend fun setIsFavorite(vacancy: Vacancy, isFavorite: Boolean)
    suspend fun getFavorites(): List<Vacancy>
}