package br.com.cauezito.schedrix.domain.repository

import br.com.cauezito.schedrix.domain.model.Appointment

interface AppointmentRepository {
    suspend fun getAvailableTimes(startDate: String, endDate: String, placeHolderDate: String): Appointment
}