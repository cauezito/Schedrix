# Schedrix

Schedrix is a simple calendar scheduling app made with **Compose Multiplatform** (Android + iOS).

Users can choose a date, pick a time, and confirm the meeting by filling in their name and email. Then, Google Calendar opens in the browser with everything pre-filled.

## Setup

- Kotlin Multiplatform plugin installed in your Android Studio (for iOS support)
- Xcode installed and configured 


## Preview

- Dark mode support  
- Calendar navigation by month  
- Validations and error feedback  
- Opens Google Calendar with pre-filled info  
- API call simulation (ktorfit + ktor)  
- Topic animation  
- Lottie animations for loading and error

<br>

## Screens

<details>
  <summary>Error + try again</summary>
  <br>
  <strong>Android</strong><br>
  
  https://github.com/user-attachments/assets/1b739ff3-5c55-4d3f-91d7-95664069e1f7

  <br><br>
  <strong>iOS</strong>

https://github.com/user-attachments/assets/3d2db585-d147-434d-a724-33bf4d47a884

</details>

<details>
  <summary>Dark mode</summary>
  <strong>Android</strong><br>
  
  https://github.com/user-attachments/assets/b7ecc4ad-eedf-43a3-bf90-e48febcf0ec9

  <br><br>
  <strong>iOS</strong><br>
  
  https://github.com/user-attachments/assets/42d68ba0-0f25-4509-8961-b4e1be073a1f
</details>

<details>
  <summary>Redirect to Google Calendar</summary>
  <strong>Android</strong><br>
  
  https://github.com/user-attachments/assets/f5cf48ff-bd57-4c24-a52a-02e7ae8a73d6  
</details>

<details>
  <summary>App icons</summary>
  <strong>Android</strong><br>
  
 ![Screenshot 2025-04-20 at 17 56 02](https://github.com/user-attachments/assets/93eff2d8-7e75-43be-a057-43018ed9d166)

  <strong>iOS</strong><br>
  
 ![Screenshot 2025-04-20 at 17 54 46](https://github.com/user-attachments/assets/d7bf97b9-6c40-4d7f-96a1-60b49f6081ac)
</details>

<br>

## Architecture

- **MVVM**: All screens share the same logic class. I call it `ScreenModel` because of Voyager, but it's the same idea as a ViewModel.  
- **Abstraction instead of implementations**: Interfaces like `AppointmentRepository` and `AppointmentRemoteDataSource` separate contracts from logic. This keeps the UI decoupled and makes testing easier with fake implementations.  
- **No business logic in UI**: The UI just observes state. All logic lives in the ScreenModel.  
- **Expect/actual**: Some features need different platform logic. For example, opening Google Calendar uses an `Intent` on Android and `UIApplication.sharedApplication.openURL` on iOS. I use `expect` in common code and implement it with `actual` on each platform.  
- **ScreenModel tied to screen**: Voyager ensures the ScreenModel stays alive with the screen. Using only Koin could cause unwanted recreation on recomposition.  
- **Visibility control**: I used `internal` in most files to prevent leaking logic across modules and keep the codebase clean.
- **No magic numbers/hard-coded text**: Colors, strings, content descriptions, and dimensions are declared as constants to avoid hardcoding (which is considered bad practice and... ugly =P).
- **Componentization**: Components and code are isolated for reuse, following the DRY principle.
- **Content description text to non-text components**: It's important to guarantee that VoiceOver/Talkback will be able to provide the screen context to user

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

> See: AppointmentScreenModelTest.kt


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

**Coverage:**
> ⚠️ I know test coverage matters, but for now I focused on hitting 100% in the presentation layer, mainly in the `ScreenModel`, since that’s where most of the logic lives

![Screenshot 2025-04-20 at 16 33 36](https://github.com/user-attachments/assets/217983c4-1a5e-4b33-9268-16c8b495b4cf)

![Screenshot 2025-04-20 at 16 31 18](https://github.com/user-attachments/assets/1ecdee98-0032-49df-b776-26532b3e682c)

To generate this report: `./gradlew :composeApp:koverHtmlReport`


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

## Important future improvements

- Add local caching + offline support
- Deeplink support  
- UI tests
- Previews (it’s tricky in CMP because the preview is incompatible with the commonMain source set, so I had to implement a workaround by creating the preview in the androidMain source set)
