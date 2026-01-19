package dev.tomislavmiksik.peak.core.util.extensions

import java.time.LocalDate
import java.util.Locale


fun LocalDate.toMonthYearStringShort(locale: Locale = Locale.getDefault()): String {
    val monthString = this.toMonthStringShort(locale)
    val yearString = this.year.toString()
    return "$monthString $yearString"
}

fun LocalDate.toMonthStringShort(locale: Locale = Locale.getDefault()): String {
    return this.month.getDisplayName(java.time.format.TextStyle.SHORT, locale)
}
