package br.com.cauezito.schedrix.domain.useCase

import br.com.cauezito.schedrix.domain.repository.AppointmentRepository
import br.com.cauezito.schedrix.presentation.mapper.asPresentation
import br.com.cauezito.schedrix.presentation.model.AppointmentPresentation

class GetAvailableAppointmentTimesUseCase(
    private val repository: AppointmentRepository
) {
    suspend operator fun invoke(start: String, end: String): AppointmentPresentation =
        repository.getAvailableTimes(start, end).asPresentation()
}