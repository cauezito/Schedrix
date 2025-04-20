package br.com.cauezito.schedrix.extensions

internal object ValidationConstants {
    val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    const val MIN_NAME_LENGTH = 6
}