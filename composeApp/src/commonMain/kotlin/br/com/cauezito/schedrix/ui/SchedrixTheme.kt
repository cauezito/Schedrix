package br.com.cauezito.schedrix.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.cauezito.schedrix.ui.tokens.DarkColorScheme
import br.com.cauezito.schedrix.ui.tokens.LightColorScheme

@Composable
internal fun SchedrixTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}