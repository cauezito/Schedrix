package br.com.cauezito.schedrix.presentation.model

import kotlinx.datetime.LocalDate

data class AppointmentCalendarDay(
    val id: String,
    val date: LocalDate?,
    val dayNumber: Int?,
    val isAvailable: Boolean,
    val isSelected: Boolean
)