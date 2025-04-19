package br.com.cauezito.schedrix.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.extensions.DateExtensions.monthYearLabel
import br.com.cauezito.schedrix.extensions.DateExtensions.weekDays
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.presentation.components.shared.CustomIcon
import br.com.cauezito.schedrix.presentation.model.AppointmentCalendarDay
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.NEXT
import br.com.cauezito.schedrix.presentation.model.AppointmentMonth.PREVIOUS
import kotlinx.datetime.LocalDate

@Composable
internal fun AppointmentCalendarSection(
    state: AppointmentState,
    onDateSelected: (LocalDate) -> Unit,
    onChangeMonth: (AppointmentMonth) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(350.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppointmentCalendarHeader(
            monthYearLabel = state.currentMonthYear.monthYearLabel(),
            onChangeMonth = onChangeMonth
        )

        AppointmentWeekdayHeader()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (state.showContentLoading) {
                CircularProgressIndicator()
            } else {
                AppointmentCalendarGrid(
                    calendarDays = state.calendarDays,
                    selectedDate = state.selectedDate,
                    onDateSelected = onDateSelected
                )
            }
        }
    }
}

@Composable
private fun AppointmentCalendarHeader(
    monthYearLabel: String,
    onChangeMonth: (AppointmentMonth) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomIcon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Click to navigate to the previous month",
            actionParam = PREVIOUS,
            onAction = onChangeMonth
        )

        Text(
            text = monthYearLabel,
            style = MaterialTheme.typography.titleMedium
        )

        CustomIcon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Click to navigate to the next month",
            actionParam = NEXT,
            onAction = onChangeMonth
        )
    }
}

@Composable
private fun AppointmentWeekdayHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        weekDays.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun AppointmentCalendarGrid(
    calendarDays: List<AppointmentCalendarDay>,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        userScrollEnabled = false,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = calendarDays,
            key = { it.id }
        ) { day ->
            if (day.date == null || day.dayNumber == null) {
                Spacer(modifier = Modifier.size(40.dp))
            } else {
                val isSelected = day.date == selectedDate

                val backgroundColor = when {
                    isSelected -> Color(0xFF1E88E5)
                    day.isAvailable -> Color(0xFFFFB74D)
                    else -> Color.LightGray
                }

                val fontColor = when {
                    isSelected -> Color.White
                    day.isAvailable -> Color.Black
                    else -> Color.DarkGray
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .background(
                            color = backgroundColor,
                            shape = MaterialTheme.shapes.small
                        )
                        .clickable(
                            enabled = day.isAvailable,
                            onClick = { onDateSelected(day.date) }
                        )
                ) {
                    Text(
                        text = day.dayNumber.toString(),
                        textAlign = TextAlign.Center,
                        color = fontColor
                    )
                }
            }
        }
    }
}