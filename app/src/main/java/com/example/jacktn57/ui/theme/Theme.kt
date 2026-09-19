package com.example.jacktn57.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Amber500,
    onPrimary = Navy900,
    primaryContainer = Navy700,
    onPrimaryContainer = Amber100,
    secondary = CyanAccent,
    onSecondary = Navy900,
    background = Navy900,
    onBackground = Color.White,
    surface = Navy800,
    onSurface = Color.White,
    surfaceVariant = Navy700,
    onSurfaceVariant = Color(0xFFCBD5E1)
)

private val LightColorScheme = lightColorScheme(
    primary = Navy700,
    onPrimary = Color.White,
    primaryContainer = Navy800,
    onPrimaryContainer = Color.White,
    secondary = Amber500,
    onSecondary = Navy900,
    secondaryContainer = Amber100,
    onSecondaryContainer = Navy900,
    background = SurfaceLight,
    onBackground = TextPrimary,
    surface = CardBackground,
    onSurface = TextPrimary,
    surfaceVariant = Color(0xFFEEF2F6),
    onSurfaceVariant = TextSecondary
)

@Composable
fun JackTN57Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
