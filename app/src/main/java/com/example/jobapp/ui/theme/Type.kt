package com.example.jobapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.jobapp.R

val SFProMedium = FontFamily(Font(R.font.sf_pro_display_medium))
val SFProRegular = FontFamily(Font(R.font.sf_pro_display_regular))
val SFProSemibold = FontFamily(Font(R.font.sf_pro_display_semibold))

val Title1 = TextStyle(
    fontFamily = SFProSemibold,
    fontSize = 22.sp
)
val Title2 = TextStyle(
    fontFamily = SFProSemibold,
    fontSize = 20.sp
)
val Title3 = TextStyle(
    fontFamily = SFProMedium,
    fontSize = 16.sp
)
val Title4 = TextStyle(
    fontFamily = SFProMedium,
    fontSize = 14.sp
)
val Text1 = TextStyle(
    fontFamily = SFProRegular,
    fontSize = 14.sp
)
val ButtonText1 = TextStyle(
    fontFamily = SFProSemibold,
    fontSize = 16.sp
)
val ButtonText2 = TextStyle(
    fontFamily = SFProRegular,
    fontSize = 14.sp
)
val TabText = TextStyle(
    fontFamily = SFProRegular,
    fontSize = 10.sp
)
val Number = TextStyle(
    fontFamily = SFProRegular,
    fontSize = 7.sp
)

val Typography = Typography()