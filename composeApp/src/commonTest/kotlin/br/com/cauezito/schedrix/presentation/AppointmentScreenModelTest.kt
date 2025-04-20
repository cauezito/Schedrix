package br.com.cauezito.schedrix.presentation

import br.com.cauezito.schedrix.data.mapper.AppointmentMapper.asDomain
import br.com.cauezito.schedrix.data.remote.model.AppointmentResponse
import br.com.cauezito.schedrix.data.remote.model.AvailableTimesData
import br.com.cauezito.schedrix.domain.model.Appointment
import br.com.cauezito.schedrix.domain.useCase.GetAvailableAppointmentTimesUseCase
import br.com.cauezito.schedrix.fakes.FakeAppointmentRepository
import br.com.cauezito.schedrix.fakes.FakeFailAppointmentRepository
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlinx.datetime.LocalDate
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class AppointmentScreenModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var screenModel: AppointmentScreenModel
    private val fakeDate = LocalDate.parse("2024-04-20")
    private val testAppointment = Appointment(
        availableTimes = listOf("2024-04-20T14:00:00Z")
    )

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        val fakeRepository = FakeAppointmentRepository(testAppointment)
        val useCase = GetAvailableAppointmentTimesUseCase(fakeRepository)

        screenModel = AppointmentScreenModel(useCase)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN initial state WHEN created THEN sets currentMonthYear and timezone`() {
        val state = screenModel.state.value
        assertNotNull(state.currentMonthYear)
        assertTrue(state.currentTimezone.isNotBlank())
    }

    @Test
    fun `GIVEN appointments fetched WHEN fetchAvailableTimes THEN updates calendar and disables loading`() = testScope.runTest {
        screenModel.fetchAvailableTimes()
        advanceUntilIdle()

        val state = screenModel.state.value
        assertFalse(state.showScreenLoading)
        assertFalse(state.showContentLoading)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN current month WHEN changeCurrentMonth to PREVIOUS THEN updates state and triggers fetch`() = testScope.runTest {
        val initialMonth = screenModel.state.value.currentMonthYear
        screenModel.changeCurrentMonth(AppointmentMonth.PREVIOUS)
        advanceUntilIdle()

        val state = screenModel.state.value
        assertNotEquals(initialMonth, state.currentMonthYear)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN current month WHEN changeCurrentMonth to NEXT THEN updates state and triggers fetch`() = testScope.runTest {
        val initialMonth = screenModel.state.value.currentMonthYear
        screenModel.changeCurrentMonth(AppointmentMonth.NEXT)
        advanceUntilIdle()

        val state = screenModel.state.value
        assertNotEquals(initialMonth, state.currentMonthYear)
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN selected date with appointments WHEN changeSelectedDate THEN updates UI state accordingly`() = testScope.runTest {
        screenModel.fetchAvailableTimes()

        advanceUntilIdle()

        screenModel.changeSelectedDate(fakeDate)

        val state = screenModel.state.value
        assertEquals(fakeDate, state.selectedDate)
        assertTrue(state.selectedDateTimes.isNotEmpty())
        assertTrue(state.selectedFormattedTimes.isNotEmpty())
        assertTrue(state.selectedDayOfMonthName.isNotBlank())
        assertTrue(state.selectedDayOfWeekName.isNotBlank())
        assertTrue(state.selectedMonthName.isNotBlank())
        assertTrue(state.calendarDays.isNotEmpty())
    }

    @Test
    fun `GIVEN API throws error WHEN fetchAvailableTimes THEN sets errorMessage and disables loading`() = testScope.runTest {
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
    fun `GIVEN appointment time not matching selected date WHEN changeSelectedDate THEN selectedDateTimes remains empty`() = testScope.runTest {
        screenModel.fetchAvailableTimes()
        advanceUntilIdle()
        val differentDate = LocalDate.parse("2024-04-21")

        screenModel.changeSelectedDate(differentDate)

        val state = screenModel.state.value
        assertTrue(state.selectedDateTimes.isEmpty())
        assertTrue(state.selectedFormattedTimes.isEmpty())
    }

    @Test
    fun `GIVEN mixed valid and invalid dates WHEN fetchAvailableTimes THEN only valid dates are parsed`() = testScope.runTest {
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
        assertEquals(2, state.selectedFormattedTimes.size)
        assertTrue(state.selectedFormattedTimes.first().isNotBlank())
    }

    @Test
    fun `GIVEN multiple appointments on same day WHEN changeSelectedDate THEN shows all times`() = testScope.runTest {
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
        assertEquals(2, state.selectedFormattedTimes.size)
    }

    @Test
    fun `GIVEN sendConfirmation WHEN called THEN completes without error`() = testScope.runTest {
        screenModel.sendConfirmation("test", "test@email.com")
    }
}