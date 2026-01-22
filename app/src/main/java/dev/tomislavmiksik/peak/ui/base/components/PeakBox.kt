package dev.tomislavmiksik.peak.ui.base.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tomislavmiksik.peak.ui.theme.PeakTheme

@Composable
fun PeakBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(all = 8.dp)

    ) {
        content()
    }
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun PeakBox_preview() {
    PeakTheme {
        PeakBox {
            Text(text = "Content inside PeakBox")
        }
    }
}
//endregion