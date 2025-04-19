package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.domain.useCase.GetAvailableAppointmentTimesUseCase
import br.com.cauezito.schedrix.extensions.DateExtensions
import br.com.cauezito.schedrix.extensions.DateExtensions.availableTimesFromSelectedDate
import br.com.cauezito.schedrix.extensions.DateExtensions.formatAs12Hour
import br.com.cauezito.schedrix.extensions.DateExtensions.formatStartAndEnd
import br.com.cauezito.schedrix.extensions.DateExtensions.nextMonth
import br.com.cauezito.schedrix.extensions.DateExtensions.previousMonth
import br.com.cauezito.schedrix.extensions.StringExtensions.capitalizeFirstChar
import br.com.cauezito.schedrix.extensions.StringExtensions.formatTimezone
import br.com.cauezito.schedrix.presentation.mapper.asPresentation
import br.com.cauezito.schedrix.presentation.mapper.mapToAppointmentCalendarDay
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.Companion.defineMonthPlaceholder
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.NEXT
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.PREVIOUS
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number

class AppointmentScreenModel(
    private val getAvailableTimes: GetAvailableAppointmentTimesUseCase
) : ScreenModel {

    private val _state = MutableStateFlow(AppointmentState())
    val state: StateFlow<AppointmentState> = _state

    private var appointments: List<AppointmentDateTime> = emptyList()
    private var choseStartAndEndDates: Pair<String, String> = Pair("", "")

    init {
        val todayDate = DateExtensions.getCurrentDate().date

        choseStartAndEndDates = todayDate.formatStartAndEnd()
        _state.value = _state.value.copy(
            currentMonthYear = todayDate,
            currentTimezone = TimeZone.currentSystemDefault().toString().formatTimezone()
        )
    }

    internal fun fetchAvailableTimes() = screenModelScope.launch {
        val monthPlaceHolder = _state.value.currentMonthYear.month.number.defineMonthPlaceholder()

        val result = getAvailableTimes(
            choseStartAndEndDates.first,
            choseStartAndEndDates.second,
            monthPlaceHolder
        ).asPresentation()

        appointments = result.availableAppointments

        _state.value = _state.value.copy(
            calendarDays = mapToAppointmentCalendarDay(
                currentMonth = _state.value.currentMonthYear,
                appointments = appointments,
                selectedDate = _state.value.selectedDate
            ),
            showScreenLoading = false,
            showContentLoading = false
        )
    }

    internal fun changeCurrentMonth(selectedMonth: AppointmentMonth) {
        val currentMonth = _state.value.currentMonthYear
        val updatedMonth = when (selectedMonth) {
            PREVIOUS -> currentMonth.previousMonth()
            NEXT -> currentMonth.nextMonth()
        }

        _state.value = _state.value.copy(
            currentMonthYear = updatedMonth,
            showContentLoading = true
        )

        choseStartAndEndDates = updatedMonth.formatStartAndEnd()

        fetchAvailableTimes()
    }

    internal fun changeSelectedDate(date: LocalDate) {
        val availableTimes = date.availableTimesFromSelectedDate(appointments)
        val formattedTimes = availableTimes.map { time ->
            time.availableAppointmentDateTime.time.formatAs12Hour()
        }

        _state.value = _state.value.copy(
            selectedDate = date,
            selectedDateTimes = availableTimes,
            selectedDayOfWeekName = date.dayOfWeek.name.capitalizeFirstChar(),
            selectedMonthName = date.month.name.capitalizeFirstChar(),
            selectedDayOfMonthName = date.dayOfMonth.toString(),
            selectedFormattedTimes = formattedTimes,
            calendarDays = mapToAppointmentCalendarDay(
                currentMonth = _state.value.currentMonthYear,
                appointments = appointments,
                selectedDate = date
            )
        )
    }

    internal fun sendConfirmation(
        name: String,
        email: String
    ) {
        //TODO validate fields
    }
}