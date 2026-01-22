package dev.tomislavmiksik.peak.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import dev.tomislavmiksik.peak.ui.theme.PeakTheme
import dev.tomislavmiksik.peak.ui.theme.primaryLight

@Composable
fun DailyPeakMountain(
    modifier: Modifier = Modifier,
    steps: Long,
    goal: Long = 10_000L,
) {
    val progress = (steps.toFloat() / goal).coerceIn(0f, 1f)

    Canvas(
        modifier = modifier.aspectRatio(0.9f)
    ) {
        val w = size.width
        val h = size.height

        val mountainPath = Path().apply {
            moveTo(0f, h)

            // Smooth curve to small left peak
            quadraticTo(
                w * 0.07f, h * 0.7f,   // control point
                w * 0.15f, h * 0.55f   // end point (small peak)
            )

            // Smooth down to valley
            quadraticTo(
                w * 0.20f, h * 0.65f,
                w * 0.25f, h * 0.75f   // valley
            )

            // Smooth up to main peak
            quadraticTo(
                w * 0.40f, h * 0.3f,
                w * 0.55f, h * 0.08f   // main peak
            )

            // Smooth to shoulder
            quadraticTo(
                w * 0.60f, h * 0.10f,
                w * 0.65f, h * 0.15f   // shoulder
            )

            // Smooth down to right base
            quadraticTo(
                w * 0.82f, h * 0.5f,
                w, h * 0.85f           // right base
            )

            lineTo(w, h)
            close()
        }
        // Unfilled mountain
        drawPath(mountainPath, color = primaryLight.copy(alpha = 0.2f))

        // Fill from bottom
        if (progress > 0f) {
            val fillTop = h - (h * progress)

            clipPath(mountainPath) {
                drawRect(
                    color = primaryLight,
                    topLeft = Offset(0f, fillTop),
                    size = Size(w, h - fillTop)
                )
            }
        }
    }
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun DailyPeakMountain_preview() {
    PeakTheme {
        DailyPeakMountain(
            steps = 7500,
            goal = 10000
        )
    }
}
//endregion
