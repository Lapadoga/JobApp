package com.example.jobapp.ui.navigation

sealed class NavScreens(val routeName: String) {
    data object VacanciesListScreen : NavScreens(VACANCIES_LIST_SCREEN)
    data object FavoritesListScreen : NavScreens(FAVORITES_LIST_SCREEN)
    data object MainListScreen : NavScreens(MAIN_LIST_SCREEN)
    data object Stub : NavScreens(STUB_SCREEN)

    companion object {
        private const val VACANCIES_LIST_SCREEN = "vacancies_list"
        private const val FAVORITES_LIST_SCREEN = "favorites_list"
        private const val MAIN_LIST_SCREEN = "main_list_screen"
        private const val STUB_SCREEN = "stub_screen"
    }
}