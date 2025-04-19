package br.com.cauezito.schedrix.data.repository

import br.com.cauezito.schedrix.data.mapper.AppointmentMapper.asDomain
import br.com.cauezito.schedrix.data.remote.datasource.AppointmentRemoteDataSource
import br.com.cauezito.schedrix.domain.repository.AppointmentRepository

class AppointmentRepositoryImpl(
    private val remoteDataSource: AppointmentRemoteDataSource
) : AppointmentRepository {

    override suspend fun getAvailableTimes(
        startDate: String,
        endDate: String,
        placeHolderDate: String
    ) = remoteDataSource.getAvailableTimes(startDate, endDate, placeHolderDate).asDomain()
}