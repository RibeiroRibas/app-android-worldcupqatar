package com.ribeiroribas.worldcupqatar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = RedSecondaryMedium,
    onPrimary = Color.White,
    surface = RedSecondaryMedium,
    onSurface = Color.White,
    background = RedSecondaryDark,
    onBackground = Color.White
)

private val LightColorPalette = lightColors(
    primary = RedSecondaryMedium,
    onPrimary = Color.White,
    surface = RedSecondaryMedium,
    onSurface = Color.White,
    background = RedSecondaryDark,
    onBackground = Color.White,
    secondary = Color.White,
    onSecondary = RedSecondaryDark
)

@Composable
fun WorldCupQatarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}