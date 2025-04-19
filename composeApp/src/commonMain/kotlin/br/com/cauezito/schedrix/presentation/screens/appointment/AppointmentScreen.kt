package br.com.cauezito.schedrix.presentation.screens.appointment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import br.com.cauezito.schedrix.presentation.AppointmentScreenModel
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import br.com.cauezito.schedrix.presentation.screens.time.AppointmentTimeScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.LocalDate
import org.koin.core.scope.Scope

internal class AppointmentScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = koinScreenModel<AppointmentScreenModel>()

        val state by screenModel.state.collectAsState()
        val onChangeMonth: (AppointmentMonth) -> Unit = screenModel::changeCurrentMonth
        val onDateSelected: (LocalDate) -> Unit = { date ->
            screenModel.changeSelectedDate(date)
            navigator.push(AppointmentTimeScreen())
        }

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