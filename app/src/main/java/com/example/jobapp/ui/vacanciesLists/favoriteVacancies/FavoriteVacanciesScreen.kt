package com.example.jobapp.ui.vacanciesLists.favoriteVacancies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.jobapp.R
import com.example.jobapp.ui.theme.Grey3
import com.example.jobapp.ui.theme.Text1
import com.example.jobapp.ui.theme.Title1
import com.example.jobapp.ui.theme.White
import com.example.jobapp.ui.vacanciesLists.VacanciesViewModel
import com.example.jobapp.ui.vacanciesLists.mainList.VacancyList

@Composable
fun FavoriteVacanciesScreen(
    viewModel: VacanciesViewModel,
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    val state by viewModel.listState.collectAsState()
    val scrollState = rememberScrollState()

    if (state.offers.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(R.string.favorite),
                style = Title1,
                color = White,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen._16dp),
                    top = dimensionResource(R.dimen._32dp)
                )
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._25dp),
                color = Color.Transparent
            )
            Text(
                text = pluralStringResource(
                    R.plurals.number_of_vacancies,
                    state.numberOfFavorites,
                    state.numberOfFavorites
                ),
                style = Text1,
                color = Grey3,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen._16dp)
                )
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._16dp),
                color = Color.Transparent
            )
            VacancyList(
                state.vacancies.filter { it.isFavorite },
                navHostController
            ) {
                viewModel.onFavoriteClick(it)
            }
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._8dp),
                color = Color.Transparent
            )
        }
    }
}