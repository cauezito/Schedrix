package br.com.cauezito.schedrix.presentation.screens.appointment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import br.com.cauezito.schedrix.ui.components.shared.CustomAnimation
import br.com.cauezito.schedrix.ui.tokens.AnimationPaths.ERROR_PATH
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_12
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_24
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_28
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_32
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_36
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_8
import br.com.cauezito.schedrix.ui.tokens.Strings.CONTENT_DESCRIPTION_ERROR
import br.com.cauezito.schedrix.ui.tokens.Strings.ERROR_DESCRIPTION_MESSAGE
import br.com.cauezito.schedrix.ui.tokens.Strings.ERROR_TITLE_MESSAGE
import br.com.cauezito.schedrix.ui.tokens.Strings.ERROR_TRY_AGAIN_BUTTON

@Composable
internal fun AppointmentErrorSection(
    onTryAgain: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimens_24),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimens_8)
        ) {
            CustomAnimation(
                animationPath = ERROR_PATH,
                contentDescription = CONTENT_DESCRIPTION_ERROR
            )

            Text(
                text = ERROR_TITLE_MESSAGE,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Text(
                text = ERROR_DESCRIPTION_MESSAGE,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dimens_36))

            Button(
                onClick = onTryAgain,
                shape = RoundedCornerShape(dimens_28),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                contentPadding = PaddingValues(horizontal = dimens_32, vertical = dimens_12)
            ) {
                Text(
                    text = ERROR_TRY_AGAIN_BUTTON,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}