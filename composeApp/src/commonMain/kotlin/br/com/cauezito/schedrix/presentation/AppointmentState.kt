package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.extensions.DateExtensions
import br.com.cauezito.schedrix.presentation.model.AppointmentCalendarDay
import kotlinx.datetime.LocalDate

data class AppointmentState(
    val showScreenLoading: Boolean = true,
    val showContentLoading: Boolean = false,
    val showError: Boolean = false,
    val currentMonthYear: LocalDate = DateExtensions.getCurrentDate().date,
    val interviewerName: String = "Cauê Santos",
    val selectedDate: LocalDate? = null,
    val calendarDays: List<AppointmentCalendarDay> = emptyList()
)