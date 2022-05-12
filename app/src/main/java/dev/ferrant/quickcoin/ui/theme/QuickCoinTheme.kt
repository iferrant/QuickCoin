package dev.ferrant.quickcoin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColorScheme()

private val LightColorPalette = lightColorScheme(
    primary = blueDarker,
    onPrimary = Color.White,
    primaryContainer = blueLighter,
    onPrimaryContainer = blueDark,
    secondary = yellow,
    onSecondary = Color.Black,
    secondaryContainer = yellowLighter,
    onSecondaryContainer = yellowDark,
    background = blueDarkest,
    onBackground = Color.White,
    surface = blueDarkest,
    onSurface = Color.White,
    surfaceVariant = blueGray,
    onSurfaceVariant = Color.White,
    error = Color.Red,
    onError = Color.White,
    errorContainer = redLighter,
    onErrorContainer = redDark,
)

@Composable
fun QuickCoinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
