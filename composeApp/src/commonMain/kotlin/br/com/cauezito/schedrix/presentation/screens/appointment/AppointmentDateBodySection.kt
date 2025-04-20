package br.com.cauezito.schedrix.presentation.screens.appointment

import BackgroundedBox
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import br.com.cauezito.schedrix.ui.components.shared.CustomAnimation
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_28
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_3
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_4
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_6
import br.com.cauezito.schedrix.ui.tokens.Strings.CALENDAR_CONTENT_SELECT_DATE
import br.com.cauezito.schedrix.ui.tokens.Strings.CALENDAR_MEETING_INFO_DURATION_VALUE
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import schedrix.composeapp.generated.resources.Res
import schedrix.composeapp.generated.resources.ic_clock

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
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

            Box(modifier = Modifier.fillMaxSize()) {
                Surface(
                    tonalElevation = dimens_3,
                    shape = RoundedCornerShape(dimens_28),
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
        modifier = Modifier.padding(horizontal = dimens_24, vertical = dimens_28),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppointmentMeetingInfo(currentTimeZone = state.currentTimezone)

        HorizontalDivider(Modifier.padding(vertical = dimens_24))

        Text(CALENDAR_CONTENT_SELECT_DATE, style = MaterialTheme.typography.bodySmall)

        Spacer(Modifier.height(dimens_4))

        AppointmentCalendarSection(
            state = state,
            onDateSelected = onDateSelected,
            onChangeMonth = onChangeMonth
        )
    }
}

@Composable
private fun AppointmentMeetingInfo(currentTimeZone: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(Res.drawable.ic_clock), contentDescription = null)
        Spacer(Modifier.width(dimens_6))
        Text(CALENDAR_MEETING_INFO_DURATION_VALUE, style = MaterialTheme.typography.bodyLarge)
    }

    Spacer(Modifier.height(dimens_6))
    Text(currentTimeZone, style = MaterialTheme.typography.bodyLarge)
}