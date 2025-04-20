package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.extensions.DateExtensions
import br.com.cauezito.schedrix.presentation.model.AppointmentCalendarDay
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class AppointmentState(
    val showScreenLoading: Boolean = true,
    val showContentLoading: Boolean = false,
    val showError: Boolean = false,
    val showGoogleCalendar: Boolean = false,
    val isNameValid: Boolean? = null,
    val isEmailValid: Boolean? = null,
    val currentMonthYear: LocalDate = DateExtensions.getCurrentDate().date,
    val interviewerName: String = "Cauê Santos",
    val selectedDate: LocalDate? = null,
    val selectedDayOfWeekName: String = "",
    val selectedMonthName: String = "",
    val selectedDayOfMonthName: String = "",
    val currentTimezone: String = "",
    val finalSelectedDateTime: LocalDateTime? = null,
    val selectedDateTimes: List<AppointmentDateTime> = emptyList(),
    val calendarDays: List<AppointmentCalendarDay> = emptyList()
)