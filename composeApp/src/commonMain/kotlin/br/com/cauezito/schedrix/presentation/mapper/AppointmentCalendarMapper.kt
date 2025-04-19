package br.com.cauezito.schedrix.presentation.mapper

import br.com.cauezito.schedrix.extensions.DateExtensions.firstDayOfMonth
import br.com.cauezito.schedrix.extensions.DateExtensions.firstDayOfWeek
import br.com.cauezito.schedrix.extensions.DateExtensions.getCurrentDate
import br.com.cauezito.schedrix.extensions.DateExtensions.nextMonth
import br.com.cauezito.schedrix.presentation.model.AppointmentCalendarDay
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil
import kotlin.math.ceil

internal fun mapToAppointmentCalendarDay(
    currentMonth: LocalDate? = null,
    appointments: List<AppointmentDateTime>,
    selectedDate: LocalDate? = null
): List<AppointmentCalendarDay> {
    val currentDate = currentMonth ?: getCurrentDate().date
    val firstDay = currentDate.firstDayOfMonth()
    val firstDayOfWeek = firstDay.firstDayOfWeek()
    val daysInMonth = firstDay.daysUntil(currentDate.nextMonth())
    val totalSlots = ceil((firstDayOfWeek + daysInMonth) / 7.0).toInt() * 7

    return buildList {
        for (index in 0 until totalSlots) {
            val dayNumber = index - firstDayOfWeek + 1

            if (dayNumber < 1 || dayNumber > daysInMonth) {
                add(
                    AppointmentCalendarDay(
                        id = "empty-$index",
                        date = null,
                        dayNumber = null,
                        isAvailable = false,
                        isSelected = false
                    )
                )
            } else {
                val date = LocalDate(firstDay.year, firstDay.month, dayNumber)
                val isAvailable = appointments.any { it.availableAppointmentDateTime.date == date }
                val isSelected = selectedDate == date

                add(
                    AppointmentCalendarDay(
                        id = "date-${date.year}-${date.monthNumber}-${date.dayOfMonth}",
                        date = date,
                        dayNumber = dayNumber,
                        isAvailable = isAvailable,
                        isSelected = isSelected
                    )
                )
            }
        }
    }
}