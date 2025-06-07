package com.example.jobapp.ui.vacanciesLists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.vacancy.Vacancy
import com.example.domain.useCase.LoadVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacanciesViewModel @Inject constructor(
    private val useCase: LoadVacanciesUseCase
) : ViewModel() {
    private val _listState = MutableStateFlow(VacanciesState())
    val listState: StateFlow<VacanciesState> = _listState.asStateFlow()

    init {
        viewModelScope.launch {
            val data = useCase.getPresentationData()
            if (data != null) {
                var numberOfFavorites = 0
                data.vacancies.forEach {
                    if (it.isFavorite)
                        numberOfFavorites++
                }
                _listState.update { state ->
                    state.copy(
                        vacancies = data.vacancies,
                        offers = data.offers,
                        favoriteVacancies = data.vacancies.filter { it.isFavorite },
                        currentPage = Pages.SEARCH
                    )
                }
            }
        }
    }

    fun onPageChange(newPage: Pages) {
        _listState.update {
            it.copy(
                currentPage = newPage
            )
        }
    }

    fun onFavoriteClick(vacancy: Vacancy) {
        val newFavoriteState = !vacancy.isFavorite
        viewModelScope.launch {
            useCase.setIsFavorite(vacancy, newFavoriteState)
            _listState.update { state ->
                state.copy(
                    vacancies = state.vacancies.map {
                        if (it == vacancy)
                            it.copy(isFavorite = newFavoriteState)
                        else
                            it
                    },
                    favoriteVacancies = if (newFavoriteState)
                        state.favoriteVacancies + vacancy.copy(isFavorite = true)
                    else
                        state.favoriteVacancies - vacancy
                )
            }
        }
    }
}