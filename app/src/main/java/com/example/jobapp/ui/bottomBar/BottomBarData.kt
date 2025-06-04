package com.example.jobapp.ui.bottomBar

import com.example.jobapp.R
import com.example.jobapp.ui.navigation.NavScreens
import com.example.jobapp.ui.vacanciesLists.Pages

fun buildBottomBarItems() = listOf(
    BottomBarRowParams(
        iconRes = R.drawable.ic_search,
        textRes = R.string.search,
        route = NavScreens.MainListScreen,
        page = Pages.SEARCH
    ),
    BottomBarRowParams(
        iconRes = R.drawable.ic_heart,
        textRes = R.string.favorite,
        route = NavScreens.FavoritesListScreen,
        page = Pages.FAVORITES
    ),
    BottomBarRowParams(
        iconRes = R.drawable.ic_letter,
        textRes = R.string.responses,
        route = NavScreens.Stub,
        page = Pages.RESPONSES
    ),
    BottomBarRowParams(
        iconRes = R.drawable.ic_message,
        textRes = R.string.messages,
        route = NavScreens.Stub,
        page = Pages.MESSAGES
    ),
    BottomBarRowParams(
        iconRes = R.drawable.ic_profile,
        textRes = R.string.profile,
        route = NavScreens.Stub,
        page = Pages.PROFILE
    ),
)