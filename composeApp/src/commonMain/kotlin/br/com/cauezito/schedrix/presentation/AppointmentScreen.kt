package br.com.cauezito.schedrix.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.com.cauezito.schedrix.presentation.components.AppointmentDateBodySection
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import kotlinx.datetime.LocalDate

internal class AppointmentScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<AppointmentScreenModel>()
        val state by screenModel.state.collectAsState()
        val onChangeMonth: (AppointmentMonth) -> Unit = screenModel::changeCurrentMonth
        val onDateSelected: (LocalDate) -> Unit = screenModel::changeSelectedDate

        LaunchedEffect(screenModel) {
            screenModel.fetchAvailableTimes()
        }

        AppointmentScreenContent(
            state = state,
            onDateSelected = onDateSelected,
            onChangeMonth = onChangeMonth
        )
    }

    @Composable
    private fun AppointmentScreenContent(
        state: AppointmentState,
        onDateSelected: (LocalDate) -> Unit,
        onChangeMonth: (AppointmentMonth) -> Unit
    ) {
        AppointmentDateBodySection(
            state = state,
            onDateSelected = onDateSelected,
            onChangeMonth = onChangeMonth
        )
    }
}


