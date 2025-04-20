package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.domain.useCase.GetAvailableAppointmentTimesUseCase
import br.com.cauezito.schedrix.extensions.DateExtensions
import br.com.cauezito.schedrix.extensions.DateExtensions.availableTimesFromSelectedDate
import br.com.cauezito.schedrix.extensions.DateExtensions.formatStartAndEnd
import br.com.cauezito.schedrix.extensions.DateExtensions.nextMonth
import br.com.cauezito.schedrix.extensions.DateExtensions.previousMonth
import br.com.cauezito.schedrix.extensions.StringExtensions.capitalizeFirstChar
import br.com.cauezito.schedrix.extensions.StringExtensions.formatTimezone
import br.com.cauezito.schedrix.extensions.ValidationConstants.EMAIL_REGEX
import br.com.cauezito.schedrix.extensions.ValidationConstants.MIN_NAME_LENGTH
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

    private val todayDate = DateExtensions.getCurrentDate().date
    private val currentTimeZone = TimeZone.currentSystemDefault().toString().formatTimezone()
    private var appointments: List<AppointmentDateTime> = emptyList()
    private var choseStartAndEndDates: Pair<String, String> = Pair("", "")

    init {
        choseStartAndEndDates = todayDate.formatStartAndEnd()
        _state.value = _state.value.copy(
            currentMonthYear = todayDate,
            currentTimezone = currentTimeZone
        )
    }

    internal fun fetchAvailableTimes() = screenModelScope.launch {
        try {
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
        } catch (_: Exception) {
            _state.value = _state.value.copy(
                showScreenLoading = false,
                showContentLoading = false,
                showError = true
            )
        }
    }

    internal fun changeCurrentMonth(selectedMonth: AppointmentMonth) {
        val currentMonth = _state.value.currentMonthYear
        val updatedMonth = when (selectedMonth) {
            PREVIOUS -> currentMonth.previousMonth()
            NEXT -> currentMonth.nextMonth()
        }

        _state.value = _state.value.copy(
            currentMonthYear = updatedMonth,
            showContentLoading = true,
            isNameValid = null,
            isEmailValid = null,
        )

        choseStartAndEndDates = updatedMonth.formatStartAndEnd()

        fetchAvailableTimes()
    }

    internal fun changeSelectedDate(date: LocalDate) {
        val availableTimes = date.availableTimesFromSelectedDate(appointments)

        _state.value = _state.value.copy(
            selectedDate = date,
            selectedDateTimes = availableTimes,
            selectedDayOfWeekName = date.dayOfWeek.name.capitalizeFirstChar(),
            selectedMonthName = date.month.name.capitalizeFirstChar(),
            selectedDayOfMonthName = date.dayOfMonth.toString(),
            isNameValid = null,
            isEmailValid = null,
            finalSelectedDateTime = null,
            calendarDays = mapToAppointmentCalendarDay(
                currentMonth = _state.value.currentMonthYear,
                appointments = appointments,
                selectedDate = date
            )
        )
    }

    internal fun selectAppointmentTime(dateTime: AppointmentDateTime) {
        _state.value = _state.value.copy(
            isNameValid = null,
            isEmailValid = null,
            showGoogleCalendar = false,
            finalSelectedDateTime = dateTime.availableAppointmentDateTime
        )
    }

    internal fun tryAgainAfterError() {
        _state.value = _state.value.copy(
            currentMonthYear = todayDate,
            currentTimezone = currentTimeZone,
            showContentLoading = true,
            isNameValid = null,
            isEmailValid = null,
            showError = false
        )

        fetchAvailableTimes()
    }

    internal fun sendConfirmation(name: String, email: String) {
        val isNameValid = name.trim().length >= MIN_NAME_LENGTH
        val isEmailValid = email.trim().matches(EMAIL_REGEX)

        _state.value = _state.value.copy(
            isNameValid = isNameValid,
            isEmailValid = isEmailValid,
            showGoogleCalendar = isNameValid && isEmailValid
        )
    }
}