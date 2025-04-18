package br.com.cauezito.schedrix.data.api

import br.com.cauezito.schedrix.data.model.AppointmentResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface AppointmentApi {
    @GET("appointment_availabilities/available_times")
    suspend fun getAvailableTimes(
        @Query("start_date_time") startDateTime: String,
        @Query("end_date_time") endDateTime: String
    ): AppointmentResponse
}