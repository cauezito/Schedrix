package br.com.cauezito.schedrix.presentation.screens.confirmation

import androidx.compose.runtime.Composable
import br.com.cauezito.schedrix.presentation.AppointmentScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

internal class AppointmentConfirmationScreen() : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<AppointmentScreenModel>()
        val onConfirmButton: (String, String) -> Unit = { name, email ->
            screenModel.sendConfirmation(name, email)
        }

        AppointmentConfirmationScreenContent(onConfirmButton = onConfirmButton)
    }

    @Composable
    private fun AppointmentConfirmationScreenContent(
        onConfirmButton: (String, String) -> Unit
    ) {
        AppointmentConfirmationSection(onConfirmButton = onConfirmButton)
    }
}