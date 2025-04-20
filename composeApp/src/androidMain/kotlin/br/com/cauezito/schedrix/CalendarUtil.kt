package br.com.cauezito.schedrix

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@Composable
actual fun openGoogleCalendar(redirectLink: String) {
    val context = LocalContext.current

    val intent = Intent(Intent.ACTION_VIEW, redirectLink.toUri())
    context.startActivity(intent)
}