package com.ribeiroribas.worldcupqatar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = RedMedium,
    onPrimary = Color.White,
    surface = RedMedium,
    onSurface = Color.White,
    background = RedDark,
    onBackground = Color.White,
    secondary = Color.White,
    onSecondary = RedDark
)

private val LightColorPalette = lightColors(
    primary = RedMedium,
    onPrimary = Color.White,
    surface = RedMedium,
    onSurface = Color.White,
    background = RedDark,
    onBackground = Color.White,
    secondary = Color.White,
    onSecondary = RedDark
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