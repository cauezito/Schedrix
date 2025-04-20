package br.com.cauezito.schedrix

import androidx.compose.runtime.Composable
import br.com.cauezito.schedrix.di.appointmentModules
import br.com.cauezito.schedrix.presentation.screens.date.AppointmentScreen
import br.com.cauezito.schedrix.ui.SchedrixTheme
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() = KoinApplication(application = { modules(appointmentModules) }) {
    SchedrixTheme {
        Navigator(AppointmentScreen())
    }
}