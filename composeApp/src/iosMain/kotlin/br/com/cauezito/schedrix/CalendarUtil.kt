package br.com.cauezito.schedrix

import androidx.compose.runtime.Composable
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Composable
actual fun openGoogleCalendar(redirectLink: String) {
    NSURL.URLWithString(redirectLink)?.let { url ->
        UIApplication.sharedApplication.openURL(url)
    }
}