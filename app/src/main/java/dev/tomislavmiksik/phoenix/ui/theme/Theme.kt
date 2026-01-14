package dev.tomislavmiksik.phoenix.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Light color scheme based on Phoenix app icon colors.
 * Primary: Warm orange from phoenix wings
 * Secondary: Gold from phoenix body
 * Tertiary: Crimson from wing stripes
 */
private val LightColorScheme = lightColorScheme(
    // Primary colors - Phoenix Orange (wings)
    primary = PhoenixOrange,
    onPrimary = Color.White,
    primaryContainer = PhoenixOrangeLight,
    onPrimaryContainer = PhoenixOrangeDark,

    // Secondary colors - Phoenix Gold (body)
    secondary = PhoenixGold,
    onSecondary = DarkGrey,
    secondaryContainer = PhoenixGoldLight,
    onSecondaryContainer = PhoenixGoldDark,

    // Tertiary colors - Phoenix Crimson (wing stripes)
    tertiary = PhoenixCrimson,
    onTertiary = Color.White,
    tertiaryContainer = PhoenixCrimsonLight,
    onTertiaryContainer = PhoenixCrimsonDark,

    // Error colors
    error = Color(0xFFB00020),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD4),
    onErrorContainer = Color(0xFF410002),

    // Background colors
    background = Color.White,
    onBackground = DarkGrey,

    // Surface colors
    surface = Color.White,
    onSurface = DarkGrey,
    surfaceVariant = LightGrey,
    onSurfaceVariant = NeutralGrey,

    // Outline
    outline = NeutralGrey,
    outlineVariant = LightGrey,
)

/**
 * Phoenix theme with light mode only.
 * Colors derived from the phoenix app icon.
 */
@Composable
fun PhoenixTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
