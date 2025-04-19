package br.com.cauezito.schedrix.ui.tokens

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val OrangeAmber = Color(0xFFFFB74D)
internal val BlueLight = Color(0xFF5CAEF5)
internal val BlueDark = Color(0xFF1976D2)

internal val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E88E5),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF5CAEF5),
    secondary = Color(0xFF1976D2),
    background = Color.White,
    surface = Color.White,
    onSurface = Color(0xFF222222),
    error = Color(0xFFD32F2F),
    onError = Color.White
)

internal val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF5CAEF5),
    onPrimary = Color(0xFF0A0A0A),
    background = Color(0xFF0D1117),
    onBackground = Color(0xFFE6EDF3),
    surface = Color(0xFF161B22),
    onSurface = Color(0xFFE6EDF3),
    primaryContainer = Color(0xFF1E2A38),
    secondary = Color(0xFF64B5F6),
    onSecondary = Color(0xFF0A0A0A),
    error = Color(0xFFF6685E),
    onError = Color(0xFF1A1A1A)
)