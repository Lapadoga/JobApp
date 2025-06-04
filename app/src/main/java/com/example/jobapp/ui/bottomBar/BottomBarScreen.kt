package com.example.jobapp.ui.bottomBar

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.jobapp.R
import com.example.jobapp.ui.theme.Blue
import com.example.jobapp.ui.theme.Grey4
import com.example.jobapp.ui.theme.Number
import com.example.jobapp.ui.theme.Red
import com.example.jobapp.ui.theme.TabText
import com.example.jobapp.ui.theme.White
import com.example.jobapp.ui.vacanciesLists.Pages
import com.example.jobapp.ui.vacanciesLists.VacanciesViewModel

@Composable
fun BottomBarScreen(
    navController: NavHostController,
    viewModel: VacanciesViewModel
) {
    val rowParams = buildBottomBarItems()
    val state by viewModel.listState.collectAsState()

    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            rowParams.forEach {
                TabElement(
                    iconRes = it.iconRes,
                    textRes = it.textRes,
                    onClick = {
                        viewModel.onPageChange(it.page)
                        navController.navigate(route = it.route.routeName)
                    },
                    isSelected = it.page == state.currentPage,
                    badgeCount = if (it.page == Pages.FAVORITES) state.numberOfFavorites else null
                )
            }
        }
    }
}

@Composable
fun TabElement(
    iconRes: Int,
    textRes: Int,
    onClick: () -> Unit,
    isSelected: Boolean,
    badgeCount: Int?
) {
    Box(
        modifier = Modifier
            .size(
                width = dimensionResource(R.dimen.tab_width),
                height = dimensionResource(R.dimen.tab_height)
            )
            .clickable {
                onClick()
            }
    ) {
        BadgedBox(
            badge = {
                if (badgeCount != null && badgeCount > 0) {
                    Badge(
                        modifier = Modifier
                            .offset(
                                x = dimensionResource(R.dimen.badge_offset_x),
                                y = dimensionResource(R.dimen.badge_offset_y),
                            ),
                        contentColor = White,
                        containerColor = Red
                    ){
                        Text(
                            text = badgeCount.toString(),
                            style = Number
                        )
                    }
                }
            },
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = stringResource(textRes),
                tint = if (isSelected) Blue else Grey4,
            )
        }
        Text(
            text = stringResource(textRes),
            style = TabText,
            color = if (isSelected) Blue else Grey4,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = dimensionResource(R.dimen.tab_text_padding)
                )
        )
    }
}

@Composable
@Preview(showBackground = true, locale = "ru")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun BottomBarScreenPreview() {
    val rowParams = buildBottomBarItems()

    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            rowParams.forEach {
                TabElement(
                    iconRes = it.iconRes,
                    textRes = it.textRes,
                    onClick = {},
                    isSelected = false,
                    badgeCount = if (it.page == Pages.FAVORITES) 10 else null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TabElementPreview() {
    val isSelected = true

    Box(
        modifier = Modifier
            .size(
                width = dimensionResource(R.dimen.tab_width),
                height = dimensionResource(R.dimen.tab_height)
            )
            .clickable { }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = stringResource(R.string.search),
            tint = if (isSelected) Blue else Grey4,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = stringResource(R.string.search),
            style = TabText,
            color = if (isSelected) Blue else Grey4,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = dimensionResource(R.dimen.tab_text_padding)
                )
        )
    }
}