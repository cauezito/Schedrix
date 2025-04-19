package br.com.cauezito.schedrix.extensions

internal object StringExtensions {
    fun String.capitalizeFirstChar() =
        lowercase().replaceFirstChar { it.uppercase() }

    fun String.formatTimezone() =
        replace("/", " - ").replace("_", " ")
}