package com.example.jobapp.ui.vacanciesLists.vacanciesList

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.jobapp.R
import com.example.jobapp.ui.theme.Blue
import com.example.jobapp.ui.theme.Grey2
import com.example.jobapp.ui.theme.Text1
import com.example.jobapp.ui.theme.White
import com.example.jobapp.ui.vacanciesLists.VacanciesViewModel
import com.example.jobapp.ui.vacanciesLists.mainList.VacancyList

@Composable
fun VacanciesListScreen(
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
            SearchRow(R.dimen.search_row_height, navHostController)
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._16dp),
                color = Color.Transparent
            )
            InfoRow(state.vacancies.size)
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._25dp),
                color = Color.Transparent
            )
            VacancyList(
                state.vacancies,
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

@Composable
fun SearchRow(
    heightRes: Int,
    navHostController: NavHostController
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(dimensionResource(heightRes))
            .padding(
                horizontal = dimensionResource(R.dimen._16dp)
            )
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .background(
                    color = Grey2,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen._8dp)
                    )
                    .clickable {
                        navHostController.navigateUp()
                    }
            )
            TextField(
                value = "",
                readOnly = true,
                onValueChange = { },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Grey2,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius)),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_list_hint),
                        style = Text1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen._8dp))
                    .fillMaxHeight()
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen._8dp)
                )
                .background(
                    color = Grey2,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
                )
                .size(dimensionResource(heightRes))
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Composable
fun InfoRow(
    numberOfVacancies: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            horizontal = dimensionResource(R.dimen._16dp)
        )
    )
    {
        Text(
            text = pluralStringResource(
                R.plurals.number_of_vacancies,
                numberOfVacancies,
                numberOfVacancies
            ),
            style = Text1,
            color = White,
            modifier = Modifier.weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.sort),
                style = Text1,
                color = Blue,
            )
            Icon(
                painter = painterResource(R.drawable.ic_sort),
                contentDescription = null,
                tint = Blue,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen._8dp)
                )
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
fun InfoRowPreview() {
    val numberOfVacancies = 6
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    )
    {
        Text(
            text = pluralStringResource(
                R.plurals.number_of_vacancies,
                numberOfVacancies,
                numberOfVacancies
            ),
            style = Text1,
            color = White,
            modifier = Modifier.weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.sort),
                style = Text1,
                color = Blue,
            )
            Icon(
                painter = painterResource(R.drawable.ic_sort),
                contentDescription = null,
                tint = Blue,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen._8dp)
                )
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
fun SearchRowPreview() {
    val heightRes = R.dimen.search_row_height
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(dimensionResource(heightRes))
            .padding(
                horizontal = dimensionResource(R.dimen._16dp)
            )
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .background(
                    color = Grey2,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen._8dp)
                    )
                    .clickable { }
            )
            TextField(
                value = "",
                readOnly = true,
                onValueChange = { },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Grey2,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius)),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_list_hint),
                        style = Text1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen._8dp))
                    .fillMaxHeight()
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen._8dp)
                )
                .background(
                    color = Grey2,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
                )
                .size(dimensionResource(heightRes))
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = null,
                tint = White
            )
        }
    }
}