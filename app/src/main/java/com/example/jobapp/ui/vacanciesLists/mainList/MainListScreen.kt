package com.example.jobapp.ui.vacanciesLists.mainList

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.example.domain.model.offer.Button
import com.example.domain.model.offer.Offer
import com.example.domain.model.vacancy.Address
import com.example.domain.model.vacancy.Experience
import com.example.domain.model.vacancy.Salary
import com.example.domain.model.vacancy.Vacancy
import com.example.jobapp.R
import com.example.jobapp.ui.navigation.NavScreens
import com.example.jobapp.ui.theme.Blue
import com.example.jobapp.ui.theme.ButtonText1
import com.example.jobapp.ui.theme.ButtonText2
import com.example.jobapp.ui.theme.Green
import com.example.jobapp.ui.theme.Grey2
import com.example.jobapp.ui.theme.Grey3
import com.example.jobapp.ui.theme.Text1
import com.example.jobapp.ui.theme.Title2
import com.example.jobapp.ui.theme.Title3
import com.example.jobapp.ui.theme.Title4
import com.example.jobapp.ui.theme.White
import com.example.jobapp.ui.vacanciesLists.VacanciesViewModel

@Composable
fun MainListScreen(
    viewModel: VacanciesViewModel,
    paddingValues: PaddingValues,
    navHostController: NavHostController,
) {
    val state by viewModel.listState.collectAsState()
    val iconsMap = iconsIds()
    val scrollState = rememberScrollState()

    if (state.offers.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            SearchRow(R.dimen.search_row_height)
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._16dp),
                color = Color.Transparent
            )
            OffersRow(state.offers, iconsMap)
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._32dp),
                color = Color.Transparent
            )
            Text(
                text = stringResource(R.string.jobs_list_title),
                color = White,
                style = Title2,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen._16dp)
                )
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._16dp),
                color = Color.Transparent
            )
            VacancyList(state.vacancies.subList(0, 3), navHostController) {
                viewModel.onFavoriteClick(it)
            }
            HorizontalDivider(
                thickness = dimensionResource(R.dimen._16dp),
                color = Color.Transparent
            )
            Button(
                onClick = {
                    navHostController.navigate(NavScreens.VacanciesListScreen.routeName)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen._8dp)),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen._16dp),
                        end = dimensionResource(R.dimen._16dp),
                        bottom = dimensionResource(R.dimen._8dp)
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = pluralStringResource(
                        R.plurals.more_jobs,
                        state.vacancies.size,
                        state.vacancies.size
                    ),
                    style = ButtonText1,
                    color = White
                )
            }
        }
    }
}

@Composable
private fun SearchRow(
    heightRes: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(dimensionResource(heightRes))
            .padding(
                horizontal = dimensionResource(R.dimen._16dp)
            )
    ) {
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
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_hint),
                    style = Text1
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(R.dimen._8dp))
                .fillMaxHeight()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Grey2,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
                )
                .size(dimensionResource(heightRes))
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun OffersRow(
    offers: List<Offer>,
    iconsMap: Map<String, Int>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8dp)),
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen._16dp)
        )
    ) {
        items(offers) { item: Offer ->
            OfferBox(item, iconsMap[item.id])
        }
    }
}

@Composable
private fun OfferBox(
    offer: Offer,
    iconRes: Int?
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                openUrl(context, offer.link)
            }
            .background(
                color = Grey2,
                shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
            )
            .size(
                width = dimensionResource(R.dimen.offer_box_width),
                height = dimensionResource(R.dimen.offer_box_height)
            )
            .padding(
                start = dimensionResource(R.dimen._8dp),
                end = dimensionResource(R.dimen.offer_box_end_padding),
                top = dimensionResource(R.dimen.offer_box_top_padding),
                bottom = dimensionResource(R.dimen.offer_box_bottom_padding)
            )
    ) {
        if (iconRes != null)
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopStart)
            )
        Text(
            text = offer.title.trim(),
            style = Title4,
            color = White,
            maxLines = if (offer.button == null) 3 else 2,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.offer_box_text_padding)
                )
        )
        if (offer.button != null) {
            val button = offer.button
            Text(
                text = button?.text ?: "",
                style = Title4,
                color = Green,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
fun VacancyList(
    vacancies: List<Vacancy>,
    navHostController: NavHostController,
    onFavoriteClick: (Vacancy) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._16dp)),
        modifier = Modifier.padding(
            horizontal = dimensionResource(R.dimen._16dp)
        )
    ) {
        vacancies.forEach {
            VacancyBox(it, navHostController) { vacancy ->
                onFavoriteClick(vacancy)
            }
        }
    }
}

@Composable
private fun VacancyBox(
    vacancy: Vacancy,
    navHostController: NavHostController,
    onFavoriteClick: (Vacancy) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                navHostController.navigate(NavScreens.StubScreen.routeName)
            }
            .background(
                color = Grey2,
                shape = RoundedCornerShape(dimensionResource(R.dimen._8dp))
            )
            .padding(dimensionResource(R.dimen._16dp))
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                if (vacancy.lookingNumber > 0) {
                    Text(
                        text = pluralStringResource(
                            R.plurals.watching_people,
                            vacancy.lookingNumber,
                            vacancy.lookingNumber
                        ),
                        color = Green,
                        style = Text1,
                    )
                    HorizontalDivider(
                        thickness = dimensionResource(R.dimen._4dp),
                        color = Color.Transparent
                    )
                }
                Text(
                    text = vacancy.title,
                    style = Title3,
                    color = White
                )
            }
            Image(
                painter = if (vacancy.isFavorite) painterResource(R.drawable.ic_heart_filled) else painterResource(
                    R.drawable.ic_heart
                ),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onFavoriteClick(vacancy)
                    }
                    .align(Alignment.TopEnd)
            )
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._10dp),
            color = Color.Transparent
        )
        Text(
            text = vacancy.address.town,
            style = Text1,
            color = White
        )
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._4dp),
            color = Color.Transparent
        )
        Row {
            Text(
                text = vacancy.company,
                style = Text1,
                color = White,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen._8dp)
                    )
            )
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                tint = Grey3
            )
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._10dp),
            color = Color.Transparent
        )
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_briefcase),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen._8dp)
                    )
            )
            Text(
                text = vacancy.experience.previewText,
                style = Text1,
                color = White
            )
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._10dp),
            color = Color.Transparent
        )
        Text(
            text = "${stringResource(R.string.posted)} ${getDayString(vacancy.publishedDate)} ${
                stringResource(getMonthStringRes(vacancy.publishedDate))
            }",
            style = Text1,
            color = Grey3
        )
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._21dp),
            color = Color.Transparent
        )
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.apply),
                style = ButtonText2,
                color = White
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
private fun VacancyBoxPreview() {
    val vacancy = Vacancy(
        id = "54a876a5-2205-48ba-9498-cfecff4baa6e",
        lookingNumber = 17,
        title = "UI/UX-дизайнер / Web-дизайнер / Дизайнер интерфейсов",
        address = Address(
            town = "Казань",
            street = "улица Чистопольская",
            house = "20/10"
        ),
        company = "Шафигуллин Шакир",
        experience = Experience(
            previewText = "Опыт от 1 до 3 лет",
            text = "1–3 года"
        ),
        publishedDate = "2024-03-06",
        isFavorite = true,
        salary = Salary(
            short = "20 000 до 50 000 ₽",
            full = "от 20 000 до 50 000 ₽ на руки"
        ),
        schedules = listOf(
            "частичная занятость",
            "полный день"
        ),
        description = "Мы разрабатываем мобильные приложения, web-приложения и сайты под ключ.\\n\\nНам в команду нужен UX/UI Designer!",
        responsibilities = "- Разработка дизайна Web+App (обязательно Figma)\n\n- Работа над созданием и улучшением систем;\n\n- Взаимодействие с командами frontend-разработки и backend-разработки",
        questions = listOf(
            "Где располагается место работы?",
            "Какой график работы?",
            "Как с вами связаться?"
        ),
        appliedNumber = null
    )

    Column(
        modifier = Modifier
            .background(
                color = Grey2,
                shape = RoundedCornerShape(dimensionResource(R.dimen._8dp))
            )
            .padding(dimensionResource(R.dimen._16dp))
            .clickable { }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                if (vacancy.lookingNumber > 0) {
                    Text(
                        text = pluralStringResource(
                            R.plurals.watching_people,
                            vacancy.lookingNumber,
                            vacancy.lookingNumber
                        ),
                        color = Green,
                        style = Text1,
                    )
                    HorizontalDivider(
                        thickness = dimensionResource(R.dimen._4dp),
                        color = Color.Transparent
                    )
                }
                Text(
                    text = vacancy.title,
                    style = Title3,
                    color = White
                )
            }
            Image(
                painter = if (vacancy.isFavorite) painterResource(R.drawable.ic_heart_filled) else painterResource(
                    R.drawable.ic_heart
                ),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._10dp),
            color = Color.Transparent
        )
        Text(
            text = vacancy.address.town,
            style = Text1,
            color = White
        )
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._4dp),
            color = Color.Transparent
        )
        Row {
            Text(
                text = vacancy.company,
                style = Text1,
                color = White,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen._8dp)
                    )
            )
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                tint = Grey3
            )
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._10dp),
            color = Color.Transparent
        )
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_briefcase),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen._8dp)
                    )
            )
            Text(
                text = vacancy.experience.previewText,
                style = Text1,
                color = White
            )
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._10dp),
            color = Color.Transparent
        )
        Text(
            text = "${stringResource(R.string.posted)} ${getDayString(vacancy.publishedDate)} ${
                stringResource(getMonthStringRes(vacancy.publishedDate))
            }",
            style = Text1,
            color = Grey3
        )
        HorizontalDivider(
            thickness = dimensionResource(R.dimen._21dp),
            color = Color.Transparent
        )
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.apply),
                style = ButtonText2,
                color = White
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
private fun OfferBoxPreview() {
    val offer = Offer(
        id = "level_up_resume",
        title = "Поднять резюме в поиске",
        link = "https://hh.ru/",
        button = Button(
            text = "Поднять"
        )
    )
    val iconsMap = iconsIds()

    Box(
        modifier = Modifier
            .background(
                color = Grey2,
                shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
            )
            .size(
                width = dimensionResource(R.dimen.offer_box_width),
                height = dimensionResource(R.dimen.offer_box_height)
            )
            .padding(
                start = dimensionResource(R.dimen._8dp),
                end = dimensionResource(R.dimen.offer_box_end_padding),
                top = dimensionResource(R.dimen.offer_box_top_padding),
                bottom = dimensionResource(R.dimen.offer_box_bottom_padding)
            )
    ) {
        val iconRes = iconsMap[offer.id]
        if (iconRes != null)
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopStart)
            )
        Text(
            text = offer.title.trim(),
            style = Title4,
            color = White,
            maxLines = if (offer.button == null) 3 else 2,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    top = if (iconRes != null) dimensionResource(R.dimen.offer_box_text_padding) else 0.dp
                )
        )
        if (offer.button != null) {
            val button = offer.button
            Text(
                text = button?.text ?: "",
                style = Title4,
                color = Green,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
private fun OffersRowPreview(
) {
    val iconsMap = iconsIds()
    val offersList: List<Offer> = listOf(
        Offer(
            id = "level_up_resume",
            title = "Поднять резюме в поиске",
            link = "https://hh.ru/",
            button = Button(
                text = "Поднять"
            )
        ),
        Offer(
            id = "near_vacancies",
            title = "Вакансии рядом с вами",
            link = "https://hh.ru/",
            null
        ),
        Offer(
            id = "temporary_job",
            title = "  Временная работа или подработка",
            link = "https://hh.ru/",
            null
        ),
        Offer(
            title = "Полезные статьи и советы",
            link = "https://hh.ru/",
            id = null,
            button = null
        )
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8dp))
    ) {
        items(offersList) { item: Offer ->
            OfferBox(item, iconsMap[item.id])
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
private fun SearchRowPreview() {
    val testHeight = 56.dp

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(testHeight)
    ) {
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
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_hint),
                    style = Text1
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(R.dimen._8dp))
                .fillMaxHeight()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Grey2,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.search_row_radius))
                )
                .size(testHeight)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = null,
            )
        }
    }
}

private fun iconsIds(): Map<String, Int> = mapOf(
    "near_vacancies" to R.drawable.ic_near_vacancies,
    "level_up_resume" to R.drawable.ic_level_up_resume,
    "temporary_job" to R.drawable.ic_temporary_job,
)

private fun getMonthStringRes(date: String): Int {
    val dateElements = date.split("-")
    val month = dateElements[1].toInt()
    val monthList = months()

    return monthList[month - 1]
}

private fun getDayString(date: String): Int {
    val dateElements = date.split("-")
    val day = dateElements[2].toInt()

    return day
}

private fun months() = listOf(
    R.string.january,
    R.string.february,
    R.string.march,
    R.string.april,
    R.string.may,
    R.string.june,
    R.string.july,
    R.string.august,
    R.string.september,
    R.string.october,
    R.string.november,
    R.string.december,
)

private fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = url.toUri()
    }
    context.startActivity(intent)
}