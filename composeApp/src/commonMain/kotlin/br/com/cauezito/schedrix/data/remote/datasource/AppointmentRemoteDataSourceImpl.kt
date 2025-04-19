package br.com.cauezito.schedrix.data.remote.datasource

import br.com.cauezito.schedrix.data.remote.api.AppointmentApi

class AppointmentRemoteDataSourceImpl(
    private val serverApi: AppointmentApi
) : AppointmentRemoteDataSource {
    override suspend fun getAvailableTimes(
        startDate: String,
        endDate: String,
        placeholderDate: String
    ) = serverApi.getAvailableTimes(startDate, endDate, placeholderDate)
}