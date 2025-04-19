package br.com.cauezito.schedrix.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toLocalDateTime

internal object DateExtensions {
    internal val weekDays = listOf("S", "M", "T", "W", "T", "F", "S")

    internal fun LocalDate.nextMonth(): LocalDate = when (monthNumber) {
        12 -> LocalDate(year = year + 1, monthNumber = 1, dayOfMonth = 1)
        else -> LocalDate(year = year, monthNumber = monthNumber + 1, dayOfMonth = 1)
    }

    internal fun LocalDate.previousMonth(): LocalDate = when (monthNumber) {
        1 -> LocalDate(year = year - 1, monthNumber = 12, dayOfMonth = 1)
        else -> LocalDate(year = year, monthNumber = monthNumber - 1, dayOfMonth = 1)
    }

    internal fun LocalDate.firstDayOfMonth() = LocalDate(year = year, month = month, dayOfMonth = 1)

    internal fun LocalDate.firstDayOfWeek() = firstDayOfMonth().dayOfWeek.isoDayNumber % 7

    internal fun LocalDate.daysInMonth() = firstDayOfMonth().daysUntil(nextMonth())

    internal fun LocalDate.monthYearLabel() = "${month.name.lowercase().replaceFirstChar { it.uppercase() }} $year"

    internal fun LocalDate.formatStartAndEnd(): Pair<String, String> {
        val start = LocalDateTime(year, month, dayOfMonth, 0, 0, 0)
        val end = LocalDateTime(year, month, dayOfMonth, 23, 59, 59)

        return Pair(start.toString(), end.toString())
    }

    internal fun getCurrentDate() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun LocalTime.formatAs12Hour(): String {
        val hour12 = if (hour == 0 || hour == 12) 12 else hour % 12
        val minuteStr = minute.toString().padStart(2, '0')
        val amPm = if (hour < 12) "am" else "pm"
        return "${hour12.toString().padStart(2, '0')}:${minuteStr}\n${amPm}"
    }
}