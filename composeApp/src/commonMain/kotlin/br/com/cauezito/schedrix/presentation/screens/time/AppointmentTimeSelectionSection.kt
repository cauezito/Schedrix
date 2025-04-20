package br.com.cauezito.schedrix.presentation.screens.time

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.ui.components.shared.ScaffoldStructure
import br.com.cauezito.schedrix.ui.components.shared.TimeSelectorItem
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_100
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_12
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_14
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_16
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_2
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_20
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_8
import br.com.cauezito.schedrix.ui.tokens.Strings.CALENDAR_MEETING_INFO_DURATION_TITLE
import br.com.cauezito.schedrix.ui.tokens.Strings.CALENDAR_MEETING_INFO_DURATION_VALUE
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_TIMEZONE_TITLE
import br.com.cauezito.schedrix.ui.tokens.Strings.TIME_SECTION_TOP_BAR_CONTENT_DESCRIPTION
import br.com.cauezito.schedrix.ui.tokens.Strings.appointmentQuestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppointmentTimeSelectionSection(
    state: AppointmentState,
    onSelectedTime: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    ScaffoldStructure(
        toolbarContentDescription = TIME_SECTION_TOP_BAR_CONTENT_DESCRIPTION,
        onBackPressed = onBackPressed
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
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
                    top = dimens_8,
                    bottom = dimens_24
                )
            )

            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = dimens_24),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = dimens_20,),
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
                        modifier = Modifier.padding(top = dimens_2, bottom = dimens_14)
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
                    TimeSelectorItem(
                        time = time,
                        onSelectedTime = onSelectedTime
                    )
                }
            }
        }
    }
}