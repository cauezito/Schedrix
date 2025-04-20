package br.com.cauezito.schedrix.presentation.screens.confirmation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.com.cauezito.schedrix.presentation.AppointmentScreenModel
import br.com.cauezito.schedrix.presentation.AppointmentState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal class AppointmentConfirmationScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = koinScreenModel<AppointmentScreenModel>()
        val state by screenModel.state.collectAsState()
        val onBackPressed: () -> Unit = { navigator.pop() }
        val onConfirmButton: (String, String) -> Unit = { name, email ->
            screenModel.sendConfirmation(name, email)
        }

        AppointmentConfirmationScreenContent(
            state = state,
            onConfirmButton = onConfirmButton,
            onBackPressed = onBackPressed
        )
    }

    @Composable
    private fun AppointmentConfirmationScreenContent(
        state: AppointmentState,
        onConfirmButton: (String, String) -> Unit,
        onBackPressed: () -> Unit
    ) {
        AppointmentConfirmationSection(
            state = state,
            onConfirmButton = onConfirmButton,
            onBackPressed = onBackPressed
        )
    }
}