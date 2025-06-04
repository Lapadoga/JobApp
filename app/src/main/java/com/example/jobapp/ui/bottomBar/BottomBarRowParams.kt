package com.example.jobapp.ui.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.jobapp.ui.navigation.NavScreens
import com.example.jobapp.ui.vacanciesLists.Pages

data class BottomBarRowParams(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int,
    val route: NavScreens,
    val page: Pages,
)
