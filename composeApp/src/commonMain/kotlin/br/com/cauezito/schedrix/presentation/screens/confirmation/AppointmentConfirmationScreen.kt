package br.com.cauezito.schedrix.presentation.screens.confirmation

import androidx.compose.runtime.Composable
import br.com.cauezito.schedrix.presentation.AppointmentScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal class AppointmentConfirmationScreen() : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<AppointmentScreenModel>()
        val navigator = LocalNavigator.currentOrThrow
        val onBackPressed: () -> Unit = { navigator.pop() }
        val onConfirmButton: (String, String) -> Unit = { name, email ->
            screenModel.sendConfirmation(name, email)
        }

        AppointmentConfirmationScreenContent(
            onConfirmButton = onConfirmButton,
            onBackPressed = onBackPressed
        )
    }

    @Composable
    private fun AppointmentConfirmationScreenContent(
        onConfirmButton: (String, String) -> Unit,
        onBackPressed: () -> Unit
    ) {
        AppointmentConfirmationSection(
            onConfirmButton = onConfirmButton,
            onBackPressed = onBackPressed
        )
    }
}