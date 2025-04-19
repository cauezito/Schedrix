package br.com.cauezito.schedrix.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import schedrix.composeapp.generated.resources.Res
import schedrix.composeapp.generated.resources.img_user_profile

@Composable
internal fun AppointmentDateHeaderSection(interviewerName: String) {

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 68.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(Res.drawable.img_user_profile),
                contentDescription = null
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    interviewerName,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    "Hey, let's talk with me about mobile development!",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
            }
        }
    }
}