package br.com.cauezito.schedrix.ui.components.shared

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun <T> CustomIcon(
    imageVector: ImageVector,
    contentDescription: String,
    rippleHighlight: Boolean = false,
    actionParam: T,
    onAction: (T) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = if (rippleHighlight) LocalIndication.current else null
            ) {
                onAction(actionParam)
            }
            .padding(8.dp)
    )
}
