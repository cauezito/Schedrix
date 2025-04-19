package br.com.cauezito.schedrix.data.mapper

import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse
import br.com.cauezito.schedrix.domain.model.Appointment

internal object AppointmentMapper {
    internal fun AppointmentResponse.asDomain() = Appointment(availableTimes = data.availableTimes.map { it })
}