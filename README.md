# Buddies
## _Give new life to your buddies_

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Buddies is a Kotlin multiplatform mobile application which allows the user to post buddies(pets or animals) and let them to adopt.

## Features
- Kotlin Multiplatform mobile
- Android(Jetpack compose)
- IOS (swift ui)
- MVVM architecture
- Shared viewmodel and shared DI

## Running the app
* Please Make sure you have KMM plugin installed in your android studio.
* Please run this [Buddies API](https://github.com/rvenky125/Buddies/tree/master/api-buddies) before running the app.
* You need to edit the BASE_URL the in [Constants.kt](https://github.com/rvenky125/Buddies/blob/master/shared/src/commonMain/kotlin/com/famas/buddies/util/Constants.kt)
   ```object Constants {
    const val BASE_URL = "http://YOUR_IP:8080/"
    ```
* Please edit the BASE_URL in this file also [Constants.kt](https://github.com/rvenky125/Buddies/blob/master/api-buddies/src/main/kotlin/com/famas/util/Constants.kt) which is located in the api
