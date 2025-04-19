package br.com.cauezito.schedrix.di

import br.com.cauezito.schedrix.data.remote.api.AppointmentApi
import br.com.cauezito.schedrix.data.remote.api.createAppointmentApi
import br.com.cauezito.schedrix.data.remote.datasource.AppointmentRemoteDataSource
import br.com.cauezito.schedrix.data.remote.datasource.AppointmentRemoteDataSourceImpl
import br.com.cauezito.schedrix.data.remote.network.HttpClientProvider
import br.com.cauezito.schedrix.data.repository.AppointmentRepositoryImpl
import br.com.cauezito.schedrix.di.AppointmentDi.dataLayer
import br.com.cauezito.schedrix.di.AppointmentDi.domainLayer
import br.com.cauezito.schedrix.di.AppointmentDi.presentationLayer
import br.com.cauezito.schedrix.domain.repository.AppointmentRepository
import br.com.cauezito.schedrix.domain.useCase.GetAvailableAppointmentTimesUseCase
import br.com.cauezito.schedrix.presentation.AppointmentScreenModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val appointmentModules = module {
    includes(
        dataLayer,
        domainLayer,
        presentationLayer
    )
}

private object AppointmentDi {
    val dataLayer = module {
        single<AppointmentApi> {
            HttpClientProvider
                .provideKtorfit()
                .createAppointmentApi()
        }

        single<AppointmentRepository> { AppointmentRepositoryImpl(get()) }
        single<AppointmentRemoteDataSource> { AppointmentRemoteDataSourceImpl(get()) }
    }

    val domainLayer = module {
        factory { GetAvailableAppointmentTimesUseCase(get()) }
    }

    val presentationLayer = module {
        scope(named("appointmentFlow")) {
            scoped { AppointmentScreenModel(get()) }
        }
    }
}