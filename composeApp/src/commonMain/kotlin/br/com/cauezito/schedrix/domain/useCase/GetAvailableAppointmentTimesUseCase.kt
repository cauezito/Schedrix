package br.com.cauezito.schedrix.domain.useCase

import br.com.cauezito.schedrix.domain.model.Appointment
import br.com.cauezito.schedrix.domain.repository.AppointmentRepository

class GetAvailableAppointmentTimesUseCase(
    private val repository: AppointmentRepository
) {
    suspend operator fun invoke(
        startDate: String,
        endDate: String,
        placeHolderDate: String
    ): Appointment = repository.getAvailableTimes(startDate, endDate, placeHolderDate)
}