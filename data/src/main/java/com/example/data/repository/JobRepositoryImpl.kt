package com.example.data.repository

import android.util.Log
import com.example.data.api.JobApi
import com.example.data.mapper.toDboEntity
import com.example.data.mapper.toEntity
import com.example.data.room.VacancyDao
import com.example.domain.ApiAnswer
import com.example.domain.model.vacancy.Vacancy
import com.example.domain.repository.JobRepository
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val vacanciesDao: VacancyDao,
    private val api: JobApi
) : JobRepository {
    override suspend fun getData(): ApiAnswer? {
        val data = try {
            api.getData()
        } catch (e: Exception) {
            val message = e.message ?: "Error while getting data"
            Log.e("Data request", message)
            null
        }

        return data
    }

    override suspend fun setIsFavorite(vacancy: Vacancy, isFavorite: Boolean) {
        val vacancyDbo = vacancy.toDboEntity()
        if (isFavorite)
            vacanciesDao.add(vacancyDbo)
        else
            vacanciesDao.remove(vacancyDbo)
    }

    override suspend fun getFavorites(): List<Vacancy> {
        val favoritesDbo = vacanciesDao.getAll()
        return favoritesDbo.map { it.toEntity() }
    }
}