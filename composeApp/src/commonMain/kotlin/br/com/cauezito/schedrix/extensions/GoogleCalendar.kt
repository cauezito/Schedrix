package br.com.cauezito.schedrix.extensions

import br.com.cauezito.schedrix.extensions.DateExtensions.formatLocalDateTime
import io.ktor.http.encodeURLParameter
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

internal fun generateGoogleCalendarLink(
    userName: String,
    startDateTime: LocalDateTime?,
    timezone: TimeZone = TimeZone.currentSystemDefault()
): String {
    return startDateTime?.let { local ->
        val pattern = "yyyyMMdd'T'HHmmss"

        val startInstant = local.toInstant(timezone)
        val endInstant = startInstant.plus(30, DateTimeUnit.MINUTE)

        val start = startInstant.toLocalDateTime(timezone)
        val end = endInstant.toLocalDateTime(timezone)

        val formattedStart = start.formatLocalDateTime(format = pattern)
        val formattedEnd = end.formatLocalDateTime(format = pattern)

        val encodedTitle = "Cauê Santos <> $userName".encodeURLParameter()
        val encodedDescription =
            "A quick chat to explore opportunities and insights.".encodeURLParameter()
        val encodedLocation = "UTF-8".encodeURLParameter()

        return "https://calendar.google.com/calendar/u/0/r/eventedit" +
                "?text=$encodedTitle" +
                "&details=$encodedDescription" +
                "&location=$encodedLocation" +
                "&dates=$formattedStart/$formattedEnd"
    }.orEmpty()
}