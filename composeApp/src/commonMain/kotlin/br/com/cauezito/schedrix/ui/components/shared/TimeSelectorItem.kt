package br.com.cauezito.schedrix.ui.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.extensions.DateExtensions.formatAs12Hour
import br.com.cauezito.schedrix.presentation.model.AppointmentDateTime
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_4
import br.com.cauezito.schedrix.ui.tokens.OrangeAmber

@Composable
internal fun TimeSelectorItem(
    time: AppointmentDateTime,
    onSelectedTime: (AppointmentDateTime) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = OrangeAmber),
        modifier = Modifier
            .padding(dimens_4)
            .clickable { onSelectedTime(time) },
        elevation = CardDefaults.cardElevation(defaultElevation = dimens_4)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time.availableAppointmentDateTime.time.formatAs12Hour(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}