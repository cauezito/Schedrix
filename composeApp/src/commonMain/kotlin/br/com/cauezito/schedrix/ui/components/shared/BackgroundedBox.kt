import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import br.com.cauezito.schedrix.ui.tokens.BlueDark
import br.com.cauezito.schedrix.ui.tokens.BlueLight

@Composable
internal fun BackgroundedBox(
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    val background = if (isDark) {
        Brush.verticalGradient(
            colors = listOf(
                colorScheme.background,
                colorScheme.surface
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                BlueLight,
                colorScheme.primary,
                BlueDark,
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        content()
    }
}
