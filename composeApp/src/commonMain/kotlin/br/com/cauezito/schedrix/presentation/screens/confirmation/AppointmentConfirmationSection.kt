package br.com.cauezito.schedrix.presentation.screens.confirmation

import BackgroundedBox
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CONFIRMATION_SECTION_CONFIRM_BUTTON
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CONFIRMATION_SECTION_DESCRIPTION
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CONFIRMATION_SECTION_INPUT_EMAIL
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CONFIRMATION_SECTION_INPUT_NAME
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.CONFIRMATION_SECTION_TITLE
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_12
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_16
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_32
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_40
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_44
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_46
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_8
import br.com.cauezito.schedrix.ui.tokens.Weight.X_SMALL
import br.com.cauezito.schedrix.ui.tokens.Weight.X_SMALL2

@Composable
internal fun AppointmentConfirmationSection(
    onConfirmButton: (String, String) -> Unit
) {
    BackgroundedBox {
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        Column {
            Spacer(modifier = Modifier.height(dimens_32))

            Text(
                text = CONFIRMATION_SECTION_TITLE,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(
                    start = dimens_16,
                    end = dimens_16,
                    top = dimens_40,
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
                StyledInputField(
                    value = name,
                    onValueChange = { name = it },
                    label = CONFIRMATION_SECTION_INPUT_NAME
                )

                StyledInputField(
                    value = email,
                    onValueChange = { email = it },
                    label = CONFIRMATION_SECTION_INPUT_EMAIL
                )

                Spacer(Modifier.height(dimens_44))

                SubmitButton(onClick = {
                    onConfirmButton(name, email)
                })
            }
        }
    }
}

@Composable
internal fun StyledInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White.copy(alpha = X_SMALL),
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White.copy(alpha = X_SMALL2),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Composable
private fun SubmitButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimens_46),
        shape = RoundedCornerShape(dimens_12),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = dimens_8,
            pressedElevation = dimens_12
        )
    ) {
        Text(
            text = CONFIRMATION_SECTION_CONFIRM_BUTTON,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}