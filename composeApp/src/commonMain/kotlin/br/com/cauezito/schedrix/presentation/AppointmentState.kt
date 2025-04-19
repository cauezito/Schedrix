package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.extensions.DateExtensions
import br.com.cauezito.schedrix.presentation.model.AppointmentCalendarDay
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import kotlinx.datetime.LocalDate

data class AppointmentState(
    val showScreenLoading: Boolean = true,
    val showContentLoading: Boolean = false,
    val showError: Boolean = false,
    val currentMonthYear: LocalDate = DateExtensions.getCurrentDate().date,
    val interviewerName: String = "Cauê Santos",
    val selectedDate: LocalDate? = null,
    val selectedDayOfWeekName: String = "",
    val selectedMonthName: String = "",
    val selectedDayOfMonthName: String = "",
    val selectedFormattedTimes: List<String> = emptyList(),
    val currentTimezone: String = "",
    val selectedDateTimes: List<AppointmentDateTime> = emptyList(),
    val calendarDays: List<AppointmentCalendarDay> = emptyList()
)