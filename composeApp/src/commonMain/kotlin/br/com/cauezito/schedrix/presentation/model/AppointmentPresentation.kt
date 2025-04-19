package br.com.cauezito.schedrix.presentation.model

import br.com.cauezito.schedrix.extensions.DateExtensions
import kotlinx.datetime.LocalDateTime

data class AppointmentPresentation(
    val availableAppointments: List<AppointmentDateTime> = listOf(),
    val interviewerName: String = "Cauê Santos",
    val durationMinutes: String = "50"
)

data class AppointmentDateTime(
    val availableAppointmentDateTime: LocalDateTime = DateExtensions.getCurrentDate()
)