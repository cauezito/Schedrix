package br.com.cauezito.schedrix.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentResponse(
    val data: AvailableTimesData
)

@Serializable
data class AvailableTimesData(
    @SerialName("available_times")
    val availableTimes: List<String>
)