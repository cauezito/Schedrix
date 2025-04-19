package br.com.cauezito.schedrix.data.remote.datasource

import br.com.cauezito.schedrix.data.remote.api.AppointmentApi

class AppointmentRemoteDataSourceImpl(
    private val serverApi: AppointmentApi
) : AppointmentRemoteDataSource {
    override suspend fun getAvailableTimes(
        start: String,
        end: String
    ) = serverApi.getAvailableTimes(start, end)
}