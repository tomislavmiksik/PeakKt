package dev.tomislavmiksik.peak.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Light color scheme based on Forest Green (#395B50).
 * Primary: Forest Green
 * Secondary: Warm Copper
 * Tertiary: Muted Teal
 */
private val LightColorScheme = lightColorScheme(
    // Primary colors - Forest Green
    primary = ForestGreen,
    onPrimary = Color.White,
    primaryContainer = ForestGreenContainer,
    onPrimaryContainer = OnForestGreenContainer,

    // Secondary colors - Warm Copper
    secondary = Copper,
    onSecondary = Color.White,
    secondaryContainer = CopperContainer,
    onSecondaryContainer = OnCopperContainer,

    // Tertiary colors - Muted Teal
    tertiary = MutedTeal,
    onTertiary = Color.White,
    tertiaryContainer = MutedTealContainer,
    onTertiaryContainer = OnMutedTealContainer,

    // Error colors
    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    // Background colors
    background = Background,
    onBackground = OnSurface,

    // Surface colors
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = Outline,

    // Outline
    outline = Outline,
    outlineVariant = OutlineVariant,
)

/**
 * Phoenix theme with light mode only.
 * Colors based on Forest Green (#395B50).
 */
@Composable
fun PeakTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
