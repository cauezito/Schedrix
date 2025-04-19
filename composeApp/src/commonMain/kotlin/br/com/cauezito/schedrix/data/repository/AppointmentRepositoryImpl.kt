package br.com.cauezito.schedrix.data.repository

import br.com.cauezito.schedrix.data.mapper.AppointmentMapper.asDomain
import br.com.cauezito.schedrix.data.remote.datasource.AppointmentRemoteDataSource
import br.com.cauezito.schedrix.domain.repository.AppointmentRepository

class AppointmentRepositoryImpl(
    private val remoteDataSource: AppointmentRemoteDataSource
) : AppointmentRepository {

    override suspend fun getAvailableTimes(
        start: String,
        end: String
    ) = remoteDataSource.getAvailableTimes(start, end).asDomain()
}