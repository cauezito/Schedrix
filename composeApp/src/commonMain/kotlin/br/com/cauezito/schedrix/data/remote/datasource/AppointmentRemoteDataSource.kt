package br.com.cauezito.schedrix.data.remote.datasource

import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse

interface AppointmentRemoteDataSource {
    suspend fun getAvailableTimes(startDate: String, endDate: String, placeholderDate: String): AppointmentResponse
}