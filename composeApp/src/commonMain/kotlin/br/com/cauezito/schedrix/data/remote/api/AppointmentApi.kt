package br.com.cauezito.schedrix.data.remote.api

import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Header
import de.jensklingenberg.ktorfit.http.Query

interface AppointmentApi {
    @GET("appointment_availabilities/available_times")
    suspend fun getAvailableTimes(
        @Query("start_date_time") startDateTime: String,
        @Query("end_date_time") endDateTime: String,
        @Header("x-mock-response-name") mockResponseName: String
    ): AppointmentResponse
}