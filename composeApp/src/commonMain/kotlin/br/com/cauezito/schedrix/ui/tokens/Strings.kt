package br.com.cauezito.schedrix.ui.tokens

internal object Strings {
    const val CALENDAR_HEADER_ARROW_BACK = "Press to navigate to the previous month"
    const val CALENDAR_HEADER_ARROW_NEXT = "Press to navigate to the next month"
    const val CALENDAR_CONTENT_SELECT_DATE = "Select a date"
    const val CALENDAR_MEETING_INFO_DURATION_TITLE = "Meeting duration"
    const val CALENDAR_MEETING_INFO_DURATION_VALUE = "30 minutes"
    const val PARTNER_WELCOME_MESSAGE = "Hey, let's talk together about"
    const val PARTNER_WELCOME_TOPIC_ANIMATION = "Topic Animation"
    const val TIME_SECTION_TOP_BAR_CONTENT_DESCRIPTION = "Back to appointment date screen"
    const val CONFIRMATION_SECTION_TOP_BAR_CONTENT_DESCRIPTION = "Back to time selection date screen"
    const val CONFIRMATION_SECTION_TITLE = "Wait... Who are you?!"
    const val CONFIRMATION_SECTION_DESCRIPTION = "Fill in your personal information to confirm this schedule. Don't worry, you'll receive a confirmation email later."
    const val CONFIRMATION_SECTION_INPUT_NAME = "Name"
    const val CONFIRMATION_SECTION_INPUT_EMAIL = "Email Address"
    const val CONFIRMATION_SECTION_CONFIRM_BUTTON = "Confirm meeting"
    const val CONFIRMATION_SECTION_NAME_INPUT_SUPPORT_TEXT = "Name must be between 5 and 35 characters and no digits"
    const val CONFIRMATION_SECTION_EMAIL_INPUT_SUPPORT_TEXT = "Enter a valid email with @"
    const val CONFIRMATION_SECTION_TIMEZONE_TITLE = "Your timezone"
    const val CONTENT_DESCRIPTION_LOADING = "Loading in progress! Wait a moment."
    const val CONTENT_DESCRIPTION_ERROR = "Error animation"
    const val ERROR_TITLE_MESSAGE = "Ops! Something went wrong"
    const val ERROR_DESCRIPTION_MESSAGE = "We encountered an issue. Please try once more."
    const val ERROR_TRY_AGAIN_BUTTON = "Try Again"

    fun appointmentQuestion(dayOfWeek: String, monthName: String, dayOfMonthName: String) =
        "Which time do you prefer in $dayOfWeek ($monthName, $dayOfMonthName) ?"
}