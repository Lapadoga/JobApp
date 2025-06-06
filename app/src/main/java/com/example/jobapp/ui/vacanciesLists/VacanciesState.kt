package com.example.jobapp.ui.vacanciesLists

import androidx.compose.runtime.Immutable
import com.example.domain.model.offer.Offer
import com.example.domain.model.vacancy.Vacancy

@Immutable
data class VacanciesState(
    val vacancies: List<Vacancy> = listOf(),
    val offers: List<Offer> = listOf(),
    val numberOfFavorites: Int = 0,
    val currentPage: Pages = Pages.SEARCH
)

enum class Pages {
    SEARCH,
    FAVORITES,
    RESPONSES,
    MESSAGES,
    PROFILE
}
