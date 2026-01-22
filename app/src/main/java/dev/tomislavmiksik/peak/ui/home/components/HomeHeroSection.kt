package dev.tomislavmiksik.peak.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.tomislavmiksik.peak.R
import dev.tomislavmiksik.peak.ui.base.components.PeakBox
import dev.tomislavmiksik.peak.ui.home.CalendarProgressTrackerData
import dev.tomislavmiksik.peak.ui.theme.PeakTheme
import java.time.LocalDate

@Composable
fun HomeHeroSection(
    modifier: Modifier = Modifier,
    steps: Long,
    goal: Long = 10_000L,
    calendarData: CalendarProgressTrackerData,
) {
    Column(modifier = modifier.fillMaxWidth()) {

        PeakBox(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CalendarProgressTracker(
                    data = calendarData,
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_sm)))
                Column {
                    DailyPeakMountain(
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.spacing_sm)),
                        steps = steps,
                        goal = goal,
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))
                    Text(
                        text = stringResource(
                            R.string.peak_steps_progress,
                            formatStepsWithCommas(steps),
                            formatStepsWithCommas(goal)
                        ),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private fun formatStepsWithCommas(steps: Long): String {
    return "%,d".format(steps)
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun HomeHeroSection_preview() {
    val today = LocalDate.now()
    val sampleSteps = (1..today.dayOfMonth).associate { day ->
        today.withDayOfMonth(day) to (5000L + (day * 500L))
    }
    val sampleCalories = (1..today.dayOfMonth).associate { day ->
        today.withDayOfMonth(day) to (1500L + (day * 50L))
    }
    PeakTheme {
        HomeHeroSection(
            steps = 7523,
            goal = 10000,
            calendarData = CalendarProgressTrackerData(
                stepsByDate = sampleSteps,
                averageStepsPerDay = 8500L,
                caloriesSpentByDate = sampleCalories,
                averageCaloriesSpentPerDay = 1800L
            )
        )
    }
}
//endregion
