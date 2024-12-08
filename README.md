# Medic Android App
Medic is a native Android application designed to provide users with a seamless experience for browsing and managing information about medicines. The app incorporates Firebase Authentication for secure user login, a searchable list of medicines, and a detailed view for each medicine.

## Installation Instructions
1. Clone the repository:
> git clone <https://github.com/KennethMathari/MedicApp>
2. Open the project in Android Studio.
3. Sync the project with Gradle.
4. Set up Firebase (Optional):
 - Add the `google-services.json` file (from your Firebase Console) to the `app` directory.
 - Ensure Firebase Authentication is enabled for email/password in the Firebase Console.
5. Build and run the app on an emulator or physical device.

## Screens
1. Login Screen
 - Users can log in using Firebase Authentication. Use `ken@test.com` as the email address & `Qwerty123` as the password if you have not setup your personal Firebase yet.
 - Error handling for invalid credentials or network issues.
2. Medicine List Screen:
 - Displays a scrollable list of medicines.
 - Includes a search bar at the top for filtering medicines by name.
3. Medicine Details Screen:
 - Provides detailed information about the selected medicine, such as name, dose, route, use etc

## API
> Medicine List API: <https://run.mocky.io/v3/147f90e2-95cd-4d6a-8e15-ae13a0f9ad9c>
>
> ## Libraries & Plugins
- <b>Jetpack Compose </b>: For building the UI in a declarative manner.
- <b>Dagger Hilt </b>: For dependency injection to manage dependencies efficiently.
- <b>Retrofit </b>: For network operations to fetch data from the API.
- <b>Kotlinx Serialization </b>: Facilitates data serialization and deserialization in a format-agnostic way.
- <b>Room </b>: For local storage.
- <b>List-Detail Layout </b>: For a dual-pane layout where one pane presents a list of items and another pane displays the details of items selected from the list.
- <b>Instantiator </b>: a little Kotlin library that uses reflection to fill data class with random test data.
- <b>KtLint</b>: creates convenient tasks in your Gradle project that run ktlint checks or do code auto format.
- <b>JUnit </b>: For unit testing.
- <b>MockK </b>: For mocking dependencies in tests.
- <b>Turbine </b> : Specialized library for testing kotlinx.coroutines Flow.

## Medic App Screenshots

## APK File
The app APK can be found from the latest successful action on the [GitHub Actions](https://github.com/KennethMathari/MedicApp/actions) tab
