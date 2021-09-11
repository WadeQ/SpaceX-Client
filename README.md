SpaceX-Client Mindera Code Test
==============

Writing SpaceX-Client App using [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/), in 100% Kotlin, using Android Jetpack Components:

Requirements
----
- Use the SpaceX API documented here:
https://documenter.getpostman.com/view/2025350/RWaEzAiG?version=latest
The "Company Info" endpoint returns the data for the section at the top of the screen,
while the "All launches" endpoint returns the data for the rest of the list.
  
- The app should look close/like this:

![](https://i.imgur.com/CDnvqiI.png)
 
- Use any libraries for Android that you want, but we will need to be able to build your code to evaluate it.

### Prerequisites - Unit Tests

#### Spek

This allows us to easily define specifications in a clear, understandable, human-readable way. This framework allows you to describe tests and expected behaviors in a more readable way.

To run tests in Android Studio you need to install Spek Framework plugin (search for Spek Framework).

The UI test run normally, either on a device or an emulator, without any special plugin or dependency.

### How it's built

* Technologies used
    * [Kotlin](https://kotlinlang.org/)
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
    * [HILT](https://developer.android.com/training/dependency-injection/hilt-android)
    * [Retrofit](https://square.github.io/retrofit/)
    * [Chucker](https://github.com/ChuckerTeam/chucker)
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Timber](https://github.com/JakeWharton/timber)
* Architecture
    * MVVM - Model View View Model

* Tests
    * [JUnit5](https://junit.org/junit5/)
    * [Spek](https://www.spekframework.org/)
    * [MockK](https://github.com/mockk/mockk)
    * [Turbine](https://github.com/cashapp/turbine)

* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
    * Plugins
        * [Spotless](https://github.com/diffplug/spotless)
        * [Dokka](https://github.com/Kotlin/dokka)
        * [jacoco](https://github.com/jacoco/jacoco)
        * [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle)
        * [Detekt](https://github.com/detekt/detekt)

* CI/CD
    * Github Actions

### Screenshots

I added some screenshots as shown:

<img src="https://i.imgur.com/3qdxfqK.jpg" width="280"/> | <img src="https://i.imgur.com/gRD1rWD.jpg" width="280"/> 
