package br.com.cauezito.schedrix.fakes

import br.com.cauezito.schedrix.domain.model.Appointment
import br.com.cauezito.schedrix.domain.repository.AppointmentRepository

internal class FakeAppointmentRepository(
    private val appointment: Appointment
) : AppointmentRepository {

    override suspend fun getAvailableTimes(
        startDate: String,
        endDate: String,
        placeHolderDate: String
    ): Appointment {
        return appointment
    }
}