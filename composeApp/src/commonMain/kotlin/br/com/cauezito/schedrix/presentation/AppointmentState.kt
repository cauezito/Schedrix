package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.extensions.DateExtensions
import br.com.cauezito.schedrix.extensions.DateExtensions.daysInMonth
import br.com.cauezito.schedrix.extensions.DateExtensions.firstDayOfMonth
import br.com.cauezito.schedrix.extensions.DateExtensions.firstDayOfWeek
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import kotlinx.datetime.LocalDate
import kotlin.math.ceil

data class AppointmentState(
    val appointments: List<AppointmentDateTime> = emptyList(),
    val showLoading: Boolean = false,
    val showError: Boolean = false,
    val currentMonthYear: LocalDate = DateExtensions.getCurrentDate().date,
    val interviewerName: String = "",
    val appointmentDuration: String = "",
    val selectedDate: LocalDate? = null
) {
    val firstDayOfMonth: LocalDate get() = currentMonthYear.firstDayOfMonth()
    val firstDayOfWeek: Int get() = currentMonthYear.firstDayOfWeek()
    val daysInMonth: Int get() = currentMonthYear.daysInMonth()
    val totalSlots: Int get() = ceil((firstDayOfWeek + daysInMonth) / 7.0).toInt() * 7
}