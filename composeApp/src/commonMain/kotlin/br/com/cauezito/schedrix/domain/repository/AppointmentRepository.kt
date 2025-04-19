package br.com.cauezito.schedrix.domain.repository

import br.com.cauezito.schedrix.domain.model.Appointment

interface AppointmentRepository {
    suspend fun getAvailableTimes(start: String, end: String): Appointment
}