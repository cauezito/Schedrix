package br.com.cauezito.schedrix.ui.components.shared

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.cauezito.schedrix.extensions.StringExtensions.formatTimezone
import br.com.cauezito.schedrix.presentation.model.timezones

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimezoneSelector(
    current: String,
    onTimezoneSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        // Prevent manual typing so the user picks from the dropdown instead
        OutlinedTextField(
            value = current.formatTimezone(),
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            timezones.forEach { zone ->
                DropdownMenuItem(
                    text = { Text(zone.formatTimezone()) },
                    onClick = {
                        onTimezoneSelected(zone)
                        expanded = false
                    }
                )
            }
        }
    }
}
