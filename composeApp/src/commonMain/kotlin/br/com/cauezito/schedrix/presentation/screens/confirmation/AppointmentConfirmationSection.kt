package br.com.cauezito.schedrix.presentation.screens.confirmation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import br.com.cauezito.schedrix.extensions.generateGoogleCalendarLink
import br.com.cauezito.schedrix.openGoogleCalendar
import br.com.cauezito.schedrix.presentation.AppointmentState
import br.com.cauezito.schedrix.ui.components.shared.CustomButton
import br.com.cauezito.schedrix.ui.components.shared.CustomInputField
import br.com.cauezito.schedrix.ui.components.shared.ScaffoldStructure
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_16
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_44
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_46
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_8
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_CONFIRM_BUTTON
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_DESCRIPTION
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_EMAIL_INPUT_SUPPORT_TEXT
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_INPUT_EMAIL
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_INPUT_NAME
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_NAME_INPUT_SUPPORT_TEXT
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_TITLE
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_TOP_BAR_CONTENT_DESCRIPTION

@Composable
internal fun AppointmentConfirmationSection(
    state: AppointmentState,
    onConfirmButton: (String, String) -> Unit,
    onBackPressed: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    if (state.showGoogleCalendar) {
        openGoogleCalendar(
            redirectLink = generateGoogleCalendarLink(
                userName = name,
                startDateTime = state.finalSelectedDateTime,
            )
        )
    }

    ScaffoldStructure(
        toolbarContentDescription = CONFIRMATION_SECTION_TOP_BAR_CONTENT_DESCRIPTION,
        onBackPressed = onBackPressed
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = CONFIRMATION_SECTION_TITLE,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(
                    start = dimens_16,
                    end = dimens_16,
                    top = dimens_8,
                    bottom = dimens_24
                )
            )

            Text(
                text = CONFIRMATION_SECTION_DESCRIPTION,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = dimens_16, end = dimens_16, bottom = dimens_46)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens_16),
                verticalArrangement = Arrangement.spacedBy(dimens_16)
            ) {
                CustomInputField(
                    value = name,
                    onValueChange = { name = it },
                    label = CONFIRMATION_SECTION_INPUT_NAME,
                    isError = state.isNameValid?.not() ?: false,
                    maxLength = 35,
                    supportingText = {
                        state.isNameValid
                            ?.takeIf { it.not() }
                            ?.let { Text(CONFIRMATION_SECTION_NAME_INPUT_SUPPORT_TEXT) }
                    }
                )

                CustomInputField(
                    value = email,
                    onValueChange = { email = it },
                    label = CONFIRMATION_SECTION_INPUT_EMAIL,
                    isError = state.isEmailValid?.not() ?: false,
                    keyboardType = KeyboardType.Email,
                    supportingText = {
                        state.isEmailValid
                            ?.takeIf { it.not() }
                            ?.let { Text(CONFIRMATION_SECTION_EMAIL_INPUT_SUPPORT_TEXT) }
                    }
                )

                Spacer(Modifier.height(dimens_44))

                CustomButton(
                    buttonText = CONFIRMATION_SECTION_CONFIRM_BUTTON,
                    onClick = { onConfirmButton(name, email) }
                )
            }
        }
    }
}