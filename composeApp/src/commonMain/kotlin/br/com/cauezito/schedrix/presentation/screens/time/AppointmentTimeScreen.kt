package br.com.cauezito.schedrix.presentation.screens.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.com.cauezito.schedrix.presentation.AppointmentScreenModel
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import br.com.cauezito.schedrix.presentation.screens.confirmation.AppointmentConfirmationScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal class AppointmentTimeScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = koinScreenModel<AppointmentScreenModel>()
        val state by screenModel.state.collectAsState()
        val onBackPressed: () -> Unit = { navigator.pop() }
        val onSelectedTime: (AppointmentDateTime) -> Unit = { dateTime ->
            screenModel.selectAppointmentTime(dateTime)
            navigator.push(AppointmentConfirmationScreen())
        }

        AppointmentTimeScreenContent(
            state = state,
            onSelectedTime = onSelectedTime,
            onBackPressed = onBackPressed
        )
    }

    @Composable
    private fun AppointmentTimeScreenContent(
        state: AppointmentState,
        onSelectedTime: (AppointmentDateTime) -> Unit,
        onBackPressed: () -> Unit
    ) {
        AppointmentTimeSelectionSection(
            state = state,
            onSelectedTime = onSelectedTime,
            onBackPressed
        )
    }
}