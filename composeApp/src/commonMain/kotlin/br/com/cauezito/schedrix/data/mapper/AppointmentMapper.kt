package br.com.cauezito.schedrix.data.mapper

import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse
import br.com.cauezito.schedrix.domain.model.Appointment
import kotlinx.datetime.Instant

internal object AppointmentMapper {
    internal fun AppointmentResponse.asDomain() = Appointment(
        availableTimes = data.availableTimes.filter { it.isValidIsoDateTime() }
    )

    private fun String.isValidIsoDateTime(): Boolean {
        return try {
            Instant.parse(this)
            true
        } catch (_: Exception) {
            false
        }
    }
}