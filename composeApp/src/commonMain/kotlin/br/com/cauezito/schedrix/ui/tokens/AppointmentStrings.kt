package br.com.cauezito.schedrix.ui.tokens

internal object AppointmentStrings {
    const val CALENDAR_HEADER_ARROW_BACK = "Press to navigate to the previous month"
    const val CALENDAR_HEADER_ARROW_NEXT = "Press to navigate to the next month"
    const val CALENDAR_CONTENT_SELECT_DATE = "Select a date"
    const val CALENDAR_MEETING_INFO_DURATION_TITLE = "Meeting duration"
    const val CALENDAR_MEETING_INFO_DURATION_VALUE = "30 minutes"
    const val PARTNER_WELCOME_MESSAGE = "Hey, let's talk with me about"
    const val PARTNER_WELCOME_TOPIC_ANIMATION = "Topic Animation"
    const val CONFIRMATION_SECTION_TITLE = "Wait... Who are you?!"
    const val CONFIRMATION_SECTION_DESCRIPTION =
        "Fill your personal information to confirm this schedule. Don't worry, you'll receive a confirmation e-mail later."
    const val CONFIRMATION_SECTION_INPUT_NAME = "Name"
    const val CONFIRMATION_SECTION_INPUT_EMAIL = "Email Address"
    const val CONFIRMATION_SECTION_CONFIRM_BUTTON = "Confirm meeting"
    const val CONFIRMATION_SECTION_TIMEZONE_TITLE = "Your timezone"

    fun appointmentQuestion(dayOfWeek: String, monthName: String, dayOfMonthName: String) =
        "Which time do you prefer in $dayOfWeek ($monthName, $dayOfMonthName)?"
}