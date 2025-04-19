package br.com.cauezito.schedrix.presentation.screens.appointment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.ui.components.shared.CustomAnimation
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import br.com.cauezito.schedrix.ui.components.shared.BackgroundedBox
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import schedrix.composeapp.generated.resources.Res
import schedrix.composeapp.generated.resources.ic_clock

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun AppointmentDateBodySection(
    state: AppointmentState,
    onDateSelected: (LocalDate) -> Unit,
    onChangeMonth: (AppointmentMonth) -> Unit,
) {
    BackgroundedBox {
        if (state.showScreenLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomAnimation()
            }
        } else {
            AppointmentDateHeaderSection(interviewerName = state.interviewerName)

            Box {
                Surface(
                    tonalElevation = 3.dp,
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 20.dp),
                ) {
                    CardContent(
                        state = state,
                        onDateSelected = onDateSelected,
                        onChangeMonth = onChangeMonth,
                    )
                }
            }
        }
    }
}

@Composable
private fun CardContent(
    state: AppointmentState,
    onDateSelected: (LocalDate) -> Unit,
    onChangeMonth: (AppointmentMonth) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppointmentMeetingInfo()

        HorizontalDivider(Modifier.padding(vertical = 24.dp))

        Text("Select a date", style = MaterialTheme.typography.bodySmall)

        Spacer(Modifier.height(4.dp))

        AppointmentCalendarSection(
            state = state,
            onDateSelected = onDateSelected,
            onChangeMonth = onChangeMonth
        )
    }
}

@Composable
private fun AppointmentMeetingInfo() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(Res.drawable.ic_clock), contentDescription = null)
        Spacer(Modifier.width(6.dp))
        Text("30 minutes", style = MaterialTheme.typography.bodyLarge)
    }

    Spacer(Modifier.height(6.dp))
    Text("Timezone: America/São Paulo", style = MaterialTheme.typography.bodyLarge)
}