package com.example.jobapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jobapp.ui.stub.StubScreen
import com.example.jobapp.ui.vacanciesLists.VacanciesViewModel
import com.example.jobapp.ui.vacanciesLists.favoriteVacancies.FavoriteVacanciesScreen
import com.example.jobapp.ui.vacanciesLists.mainList.MainListScreen
import com.example.jobapp.ui.vacanciesLists.vacanciesList.VacanciesListScreen

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: VacanciesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.MainListScreen.routeName
    ) {
        composable(
            route = NavScreens.MainListScreen.routeName
        ) {
            MainListScreen(viewModel, paddingValues, navController)
        }
        composable(
            route = NavScreens.ResponsesScreen.routeName
        ) {
            StubScreen()
        }
        composable(
            route = NavScreens.MessagesScreen.routeName
        ) {
            StubScreen()
        }
        composable(
            route = NavScreens.ProfileScreen.routeName
        ) {
            StubScreen()
        }
        composable(
            route = NavScreens.StubScreen.routeName
        ) {
            StubScreen()
        }
        composable(
            route = NavScreens.VacanciesListScreen.routeName
        ) {
            VacanciesListScreen(viewModel, paddingValues, navController)
        }
        composable(
            route = NavScreens.FavoritesListScreen.routeName
        ) {
            FavoriteVacanciesScreen(viewModel, paddingValues, navController)
        }
    }
}