package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.data.mapper.AppointmentMapper.asDomain
import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse
import br.com.cauezito.schedrix.data.remote.model.AvailableTimesData
import br.com.cauezito.schedrix.domain.model.Appointment
import br.com.cauezito.schedrix.domain.useCase.GetAvailableAppointmentTimesUseCase
import br.com.cauezito.schedrix.fakes.FakeAppointmentRepository
import br.com.cauezito.schedrix.fakes.FakeFailAppointmentRepository
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class AppointmentScreenModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var screenModel: AppointmentScreenModel

    private val fakeDate = LocalDate.parse("2024-04-20")
    private val testAppointment = Appointment(
        availableTimes = listOf("2024-04-20T14:00:00Z", "2024-04-20T15:00:00Z")
    )

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
        val fakeRepository = FakeAppointmentRepository(testAppointment)
        val useCase = GetAvailableAppointmentTimesUseCase(fakeRepository)
        screenModel = AppointmentScreenModel(useCase)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN initial state WHEN created THEN sets currentMonthYear and timezone`() =
        runTest(dispatcher) {
        val state = screenModel.state.value
        assertNotNull(state.currentMonthYear)
        assertTrue(state.currentTimezone.isNotBlank())
    }

    @Test
    fun `GIVEN appointments fetched WHEN fetchAvailableTimes THEN updates calendar and disables loading`() =
        runTest(dispatcher) {
        screenModel.fetchAvailableTimes()
        advanceUntilIdle()

        val state = screenModel.state.value
        assertFalse(state.showScreenLoading)
        assertFalse(state.showContentLoading)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN current month WHEN changeCurrentMonth to PREVIOUS THEN updates state and triggers fetch`() =
        runTest(dispatcher) {
        val initialMonth = screenModel.state.value.currentMonthYear
        screenModel.changeCurrentMonth(AppointmentMonth.PREVIOUS)
        advanceUntilIdle()

        val state = screenModel.state.value
        assertNotEquals(initialMonth, state.currentMonthYear)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN current month WHEN changeCurrentMonth to NEXT THEN updates state and triggers fetch`() =
        runTest(dispatcher) {
        val initialMonth = screenModel.state.value.currentMonthYear
        screenModel.changeCurrentMonth(AppointmentMonth.NEXT)
        advanceUntilIdle()

        val state = screenModel.state.value
        assertNotEquals(initialMonth, state.currentMonthYear)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN selected date with appointments WHEN changeSelectedDate THEN updates UI state accordingly`() =
        runTest(dispatcher) {
        screenModel.fetchAvailableTimes()
        advanceUntilIdle()

        screenModel.changeSelectedDate(fakeDate)

        val state = screenModel.state.value
        assertEquals(fakeDate, state.selectedDate)
        assertTrue(state.selectedDateTimes.isNotEmpty())
        assertTrue(state.selectedDayOfMonthName.isNotBlank())
        assertTrue(state.selectedDayOfWeekName.isNotBlank())
        assertTrue(state.selectedMonthName.isNotBlank())
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN API throws error WHEN fetchAvailableTimes THEN sets errorMessage and disables loading`() =
        runTest(dispatcher) {
        val useCase = GetAvailableAppointmentTimesUseCase(FakeFailAppointmentRepository())
        screenModel = AppointmentScreenModel(useCase)

        screenModel.fetchAvailableTimes()
        advanceUntilIdle()

        val state = screenModel.state.value
        assertFalse(state.showScreenLoading)
        assertFalse(state.showContentLoading)
        assertTrue(state.showError)
    }

    @Test
    fun `GIVEN appointment time not matching selected date WHEN changeSelectedDate THEN selectedDateTimes remains empty`() =
        runTest(dispatcher) {
        screenModel.fetchAvailableTimes()
        advanceUntilIdle()
        val differentDate = LocalDate.parse("2024-04-21")

        screenModel.changeSelectedDate(differentDate)

        val state = screenModel.state.value
        assertTrue(state.selectedDateTimes.isEmpty())
    }

    @Test
    fun `GIVEN mixed valid and invalid dates WHEN fetchAvailableTimes THEN only valid dates are parsed`() =
        runTest(dispatcher) {
        val response = AppointmentResponse(
            data = AvailableTimesData(
                availableTimes = listOf("2024-04-20T14:00:00Z", "INVALID_DATE", "2024-04-20T15:00:00Z")
            )
        )
        val appointment = response.asDomain()

        val useCase = GetAvailableAppointmentTimesUseCase(FakeAppointmentRepository(appointment))
        screenModel = AppointmentScreenModel(useCase)

        screenModel.fetchAvailableTimes()
        advanceUntilIdle()

        screenModel.changeSelectedDate(LocalDate.parse("2024-04-20"))

        val state = screenModel.state.value
        assertEquals(2, state.selectedDateTimes.size)
    }

    @Test
    fun `GIVEN multiple appointments on same day WHEN changeSelectedDate THEN shows all times`() =
        runTest(dispatcher) {
        val appointment = Appointment(
            availableTimes = listOf(
                "2024-04-20T14:00:00Z",
                "2024-04-20T15:30:00Z"
            )
        )

        val useCase = GetAvailableAppointmentTimesUseCase(FakeAppointmentRepository(appointment))

        screenModel = AppointmentScreenModel(useCase)
        screenModel.fetchAvailableTimes()
        advanceUntilIdle()
        screenModel.changeSelectedDate(LocalDate.parse("2024-04-20"))

        val state = screenModel.state.value
        assertEquals(2, state.selectedDateTimes.size)
        }

    @Test
    fun `GIVEN sendConfirmation WHEN called THEN completes without error`() = runTest(dispatcher) {
        screenModel.sendConfirmation("Valid Name", "test@email.com")
    }

    @Test
    fun `WHEN sendConfirmation with invalid email THEN sets isEmailValid false`() =
        runTest(dispatcher) {
            screenModel.sendConfirmation("Valid Name", "invalidEmail")

            val state = screenModel.state.value
            assertFalse(state.isEmailValid!!)
            assertFalse(state.showGoogleCalendar)
        }

    @Test
    fun `WHEN sendConfirmation with invalid name THEN sets isNameValid false`() =
        runTest(dispatcher) {
            screenModel.sendConfirmation("abc", "valid@example.com")

            val state = screenModel.state.value
            assertFalse(state.isNameValid!!)
            assertFalse(state.showGoogleCalendar)
        }

    @Test
    fun `WHEN sendConfirmation with valid inputs THEN sets showGoogleCalendar true`() =
        runTest(dispatcher) {
            screenModel.sendConfirmation("Valid Name", "valid@email.com")

            val state = screenModel.state.value
            assertTrue(state.isNameValid == true)
            assertTrue(state.isEmailValid == true)
            assertTrue(state.showGoogleCalendar)
        }

    @Test
    fun `WHEN selectAppointmentTime THEN updates finalSelectedDateTime correctly`() =
        runTest(dispatcher) {
            val dateTime = AppointmentDateTime(LocalDateTime.parse("2024-04-20T14:00"))
            screenModel.selectAppointmentTime(dateTime)

            val state = screenModel.state.value
            assertEquals(LocalDateTime.parse("2024-04-20T14:00"), state.finalSelectedDateTime)
        }

    @Test
    fun `WHEN tryAgainAfterError THEN resets state and fetches data again`() = runTest(dispatcher) {
        screenModel.tryAgainAfterError()
        advanceUntilIdle()

        val state = screenModel.state.value
        assertFalse(state.showError)
        assertFalse(state.showContentLoading)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN no appointments on date WHEN changeSelectedDate THEN selectedDateTimes is empty`() =
        runTest(dispatcher) {
            screenModel.fetchAvailableTimes()
            advanceUntilIdle()

            val noAppointmentDate = LocalDate.parse("2024-04-25")
            screenModel.changeSelectedDate(noAppointmentDate)

            val state = screenModel.state.value
            assertTrue(state.selectedDateTimes.isEmpty())
        }

    @Test
    fun `WHEN changeCurrentMonth to NEXT THEN updates month and fetches data`() =
        runTest(dispatcher) {
            val initialMonth = screenModel.state.value.currentMonthYear
            screenModel.changeCurrentMonth(AppointmentMonth.NEXT)
            advanceUntilIdle()

            val state = screenModel.state.value
            assertNotEquals(initialMonth, state.currentMonthYear)
            assertFalse(state.showContentLoading)
            assertTrue(state.calendarDays.isNotEmpty())
    }
}