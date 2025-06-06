package com.example.jobapp.ui.navigation

sealed class NavScreens(val routeName: String) {
    data object VacanciesListScreen : NavScreens(VACANCIES_LIST_SCREEN)
    data object FavoritesListScreen : NavScreens(FAVORITES_LIST_SCREEN)
    data object MainListScreen : NavScreens(MAIN_LIST_SCREEN)
    data object ResponsesScreen : NavScreens(RESPONSES_SCREEN)
    data object MessagesScreen : NavScreens(MESSAGES_SCREEN)
    data object ProfileScreen : NavScreens(PROFILE_SCREEN)
    data object StubScreen : NavScreens(STUB_SCREEN)

    companion object {
        const val VACANCIES_LIST_SCREEN = "vacancies_list"
        const val FAVORITES_LIST_SCREEN = "favorites_list"
        const val MAIN_LIST_SCREEN = "main_list_screen"
        const val RESPONSES_SCREEN = "responses_screen"
        const val MESSAGES_SCREEN = "messages_screen"
        const val PROFILE_SCREEN = "profile_screen"
        const val STUB_SCREEN = "stub_screen"
    }
}