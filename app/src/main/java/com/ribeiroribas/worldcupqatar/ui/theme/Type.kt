package com.ribeiroribas.worldcupqatar.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ribeiroribas.worldcupqatar.R

val Klik = FontFamily(
    Font(R.font.klik_light),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Klik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = Klik,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    body2 = TextStyle(
        fontFamily = Klik,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )
)
