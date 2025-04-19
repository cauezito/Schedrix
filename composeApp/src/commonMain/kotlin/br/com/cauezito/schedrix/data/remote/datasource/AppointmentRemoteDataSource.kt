package br.com.cauezito.schedrix.data.remote.datasource

import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse

interface AppointmentRemoteDataSource {
    suspend fun getAvailableTimes(start: String, end: String): AppointmentResponse
}