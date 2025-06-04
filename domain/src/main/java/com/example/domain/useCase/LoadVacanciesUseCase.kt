package com.example.domain.useCase

import com.example.domain.ApiAnswer
import com.example.domain.model.vacancy.Vacancy
import com.example.domain.repository.JobRepository
import javax.inject.Inject

class LoadVacanciesUseCase @Inject constructor(
    private val repository: JobRepository
) {
    suspend fun getPresentationData(): ApiAnswer? {
        val data = repository.getData()
        val result = if (data != null) {
            val favoriteVacancies = getFavorites()
            val favoriteIds = favoriteVacancies.map { it.id }
            val currentVacanciesState = data.vacancies.map {
                if (it.id in favoriteIds)
                    it.copy(isFavorite = true)
                else
                    it
            }
            data.copy(vacancies = currentVacanciesState)
        } else
            null

        return result
    }

    suspend fun setIsFavorite(vacancy: Vacancy, isFavorite: Boolean) =
        repository.setIsFavorite(vacancy, isFavorite)

    suspend fun getFavorites() = repository.getFavorites()
}

