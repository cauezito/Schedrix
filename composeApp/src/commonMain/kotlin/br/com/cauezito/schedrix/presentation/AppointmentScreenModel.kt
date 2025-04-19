package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.domain.useCase.GetAvailableAppointmentTimesUseCase
import br.com.cauezito.schedrix.extensions.DateExtensions.nextMonth
import br.com.cauezito.schedrix.extensions.DateExtensions.previousMonth
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.NEXT
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.PREVIOUS
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class AppointmentScreenModel(
    private val getAvailableTimes: GetAvailableAppointmentTimesUseCase
) : ScreenModel {

    private val _state = MutableStateFlow(AppointmentState())
    val state: StateFlow<AppointmentState> = _state

    internal fun fetchTimes(start: String, end: String) = screenModelScope.launch {
        val availableTimes = getAvailableTimes(start = start, end = end)

        _state.value = AppointmentState(
            appointments = availableTimes.availableAppointments,
            interviewerName = availableTimes.interviewerName,
            appointmentDuration = availableTimes.durationMinutes
        )
    }

    internal fun changeCurrentMonth(selectedMonth: AppointmentMonth) {
        val currentDate = _state.value.currentMonthYear
        val updatedDate = when (selectedMonth) {
            PREVIOUS -> currentDate.previousMonth()
            NEXT -> currentDate.nextMonth()
        }

        _state.value = _state.value.copy(currentMonthYear = updatedDate)
    }

    internal fun changeSelectedDate(date: LocalDate) {
        _state.value = _state.value.copy(selectedDate = date)
    }
}