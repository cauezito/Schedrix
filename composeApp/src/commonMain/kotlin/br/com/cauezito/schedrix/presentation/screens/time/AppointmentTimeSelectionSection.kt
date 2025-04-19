package br.com.cauezito.schedrix.presentation.screens.time

import BackgroundedBox
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CALENDAR_MEETING_INFO_DURATION_TITLE
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CALENDAR_MEETING_INFO_DURATION_VALUE
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CONFIRMATION_SECTION_TIMEZONE_TITLE
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.appointmentQuestion
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_1
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_100
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_12
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_14
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_16
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_2
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_20
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_32
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_58
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_62
import br.com.cauezito.schedrix.ui.tokens.Numbers.TWO
import br.com.cauezito.schedrix.ui.tokens.OrangeAmber

@Composable
internal fun AppointmentTimeSelectionSection(
    state: AppointmentState,
    onSelectedTime: (String) -> Unit
) {
    BackgroundedBox {
        Column {
            Spacer(modifier = Modifier.height(dimens_32))

            Text(
                text = appointmentQuestion(
                    dayOfWeek = state.selectedDayOfWeekName,
                    monthName = state.selectedMonthName,
                    dayOfMonthName = state.selectedDayOfMonthName
                ),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(
                    start = dimens_16,
                    end = dimens_16,
                    top = dimens_62,
                    bottom = dimens_24
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimens_24),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimens_20,
                ),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(vertical = dimens_24, horizontal = 16.dp)) {
                    Text(
                        text = CALENDAR_MEETING_INFO_DURATION_TITLE,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )

                    Text(
                        text = CALENDAR_MEETING_INFO_DURATION_VALUE,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = dimens_2, bottom = dimens_14)
                    )

                    Text(
                        text = CONFIRMATION_SECTION_TIMEZONE_TITLE,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black,
                    )

                    Text(
                        text = state.currentTimezone,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(dimens_100),
                horizontalArrangement = Arrangement.spacedBy(dimens_12),
                verticalArrangement = Arrangement.spacedBy(dimens_12),
                modifier = Modifier.fillMaxWidth().padding(all = dimens_12)
            ) {
                items(state.selectedFormattedTimes) { time ->
                    AppointmentTimeItemButton(
                        time = time,
                        onSelectedTime = onSelectedTime
                    )
                }
            }
        }
    }
}

@Composable
private fun AppointmentTimeItemButton(
    time: String,
    onSelectedTime: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimens_12))
            .border(
                border = BorderStroke(width = dimens_1, Color.White),
                shape = RoundedCornerShape(dimens_16)
            )
            .height(dimens_58)
            .background(color = OrangeAmber)
            .clickable { onSelectedTime(time) }
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = time,
            minLines = TWO
        )
    }
}