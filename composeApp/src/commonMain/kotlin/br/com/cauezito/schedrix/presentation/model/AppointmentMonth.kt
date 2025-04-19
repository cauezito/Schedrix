package br.com.cauezito.schedrix.presentation.model

private const val APRIL = 4
private const val MAY = 5
internal enum class AppointmentMonth {
    PREVIOUS,
    NEXT;

    companion object {
        fun Int.defineMonthPlaceholder(): String {
            return when(this) {
                APRIL -> "Apr2025"
                MAY -> "May2025"
                else -> "Default"
            }
        }
    }
}