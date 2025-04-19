package br.com.cauezito.schedrix.presentation.mapper

import br.com.cauezito.schedrix.domain.model.Appointment
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import br.com.cauezito.schedrix.presentation.model.AppointmentPresentation
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Appointment.asPresentation(): AppointmentPresentation {
    val availableAppointments = this.availableTimes.map { date ->
        val instant = Instant.parse(date)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        AppointmentDateTime(availableAppointmentDateTime = localDateTime)
    }

    return AppointmentPresentation(availableAppointments = availableAppointments)
}