package br.com.cauezito.schedrix.fakes

import br.com.cauezito.schedrix.domain.model.Appointment
import br.com.cauezito.schedrix.domain.repository.AppointmentRepository

internal class FakeFailAppointmentRepository : AppointmentRepository {
    override suspend fun getAvailableTimes(
        startDate: String,
        endDate: String,
        placeHolderDate: String
    ): Appointment {
        throw RuntimeException("Network failure")
    }
}