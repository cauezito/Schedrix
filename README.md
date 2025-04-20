# Schedrix

Schedrix is a simple calendar scheduling app made with **Compose Multiplatform** (Android + iOS).

Users can choose a date, pick a time, and confirm the meeting by filling in their name and email. Then, Google Calendar opens in the browser with everything pre-filled.


## Preview

- Dark mode support  
- Calendar navigation by month  
- Validations and error feedback  
- Opens Google Calendar with pre-filled info  
- API call simulation (ktorfit + ktor)  
- Topic animation  
- Lottie animations for loading and error

<br>

## Architecture

- **MVVM**: All screens share the same logic class. I call it `ScreenModel` because of Voyager, but it's the same idea as a ViewModel.  
- **Abstraction instead of implementations**: Interfaces like `AppointmentRepository` and `AppointmentRemoteDataSource` separate contracts from logic. This keeps the UI decoupled and makes testing easier with fake implementations.  
- **No business logic in UI**: The UI just observes state. All logic lives in the ScreenModel.  
- **Expect/actual**: Some features need different platform logic. For example, opening Google Calendar uses an `Intent` on Android and `UIApplication.sharedApplication.openURL` on iOS. I use `expect` in common code and implement it with `actual` on each platform.  
- **ScreenModel tied to screen**: Voyager ensures the ScreenModel stays alive with the screen. Using only Koin could cause unwanted recreation on recomposition.  
- **Visibility control**: I used `internal` in most files to prevent leaking logic across modules and keep the codebase clean.  

<br>

## Tests

- BDD-style (`GIVEN`, `WHEN`, `THEN`)  
- Uses a fake repository to simulate success, failure and alternative scenarios without relying on a real API  

**Cases:**

- Month navigation  
- Fetching and parsing appointments  
- Validations (email, name)  
- Error handling  
- Date/time selection  

<br>
> See: `AppointmentScreenModelTest.kt`
<br>

### Example test cases

| Scenario                          | Description                                   |
| --------------------------------- | --------------------------------------------- |
| Initial state                     | Validates current month and timezone          |
| Fetch appointments                | Updates calendar and disables loading         |
| Change to previous month          | Navigates to previous month and updates state |
| Change to next month              | Navigates to next month and updates state     |
| Select date/time                  | Sets state for confirmation                   |
| No appointments on date           | Keeps selectedDateTimes empty                 |
| Mixed valid and invalid dates     | Only valid dates are parsed                   |
| Multiple appointments on same day | Displays all available times                  |
| SelectAppointmentTime             | Updates finalSelectedDateTime                 |
| Retry after error                 | Resets error state and refetches data         |
| Invalid API response              | Shows error screen                            |
| Valid confirmation                | Enables "open Google Calendar" option         |
| Invalid email format              | Blocks confirmation and shows feedback        |
| Invalid name input                | Blocks confirmation and shows feedback        |
| Valid name and email              | Triggers Google Calendar integration          |

<br>

## Libraries used

| Library          | Purpose                                   |
| ---------------- | ----------------------------------------- |
| Voyager          | Navigation and screen-scoped ViewModel    |
| Koin             | Dependency injection                      |
| Ktor             | HTTP client (Android + iOS)               |
| Ktorfit          | Retrofit-style HTTP with annotations      |
| Mockk            | Mocks for unit tests                      |
| Kover            | Code coverage report                      |
| Compottie        | Lottie animation in Compose Multiplatform |
| kotlinx-datetime | Multiplatform date and time handling      |

<br>

## Important improvements

- Add local caching + offline support
- Deeplink support  
- Accessibility improvements (TalkBack/VoiceOver)  
- Add analytics for user interactions  
- UI tests
