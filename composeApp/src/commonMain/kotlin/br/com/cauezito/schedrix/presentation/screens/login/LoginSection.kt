package br.com.cauezito.schedrix.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import br.com.cauezito.schedrix.ui.components.shared.CustomInputField
import br.com.cauezito.schedrix.ui.tokens.BlueDark
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_12
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_20
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_48
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_50
import br.com.cauezito.schedrix.ui.tokens.OrangeAmber
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_CTA_CREATE_ACCOUNT
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_CTA_FORGOT_PASSWORD
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_INPUT_EMAIL
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_INPUT_LOGIN
import br.com.cauezito.schedrix.ui.tokens.Strings.CONFIRMATION_SECTION_INPUT_PASSWORD

@Composable
internal fun LoginSection(
    onLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    onCreateAccount: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = cardColors(
                containerColor = colorScheme.surface
            ),
            shape = RoundedCornerShape(dimens_24),
            modifier = Modifier
                .padding(dimens_24)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = dimens_24, vertical = dimens_48)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimens_20),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(dimens_12)) {
                    CustomInputField(
                        value = email,
                        onValueChange = { email = it },
                        label = CONFIRMATION_SECTION_INPUT_EMAIL,
                        isError = false,
                        keyboardType = KeyboardType.Email,
                        supportingText = {}
                    )

                    CustomInputField(
                        value = password,
                        onValueChange = { password = it },
                        label = CONFIRMATION_SECTION_INPUT_PASSWORD,
                        isError = false,
                        keyboardType = KeyboardType.Password,
                        supportingText = {}
                    )
                }

                Button(
                    onClick = onLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimens_50),
                    enabled = true,
                    shape = RoundedCornerShape(dimens_12)
                ) {
                    Text(CONFIRMATION_SECTION_INPUT_LOGIN)
                }

                TextButton(onClick = onForgotPassword) {
                    Text(CONFIRMATION_SECTION_CTA_FORGOT_PASSWORD, color = BlueDark)
                }

                HorizontalDivider(
                    color = colorScheme.outlineVariant,
                    modifier = Modifier.padding(vertical = dimens_12)
                )

                TextButton(onClick = onCreateAccount) {
                    Text(CONFIRMATION_SECTION_CTA_CREATE_ACCOUNT, color = OrangeAmber)
                }
            }
        }
    }
}