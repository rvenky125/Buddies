object Deps {
    // COMPOSE
    private const val activityComposeVersion = "1.6.1"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    const val composeVersion = "1.3.2"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val materialIconsExtended = "1.3.1"
    const val composeIconsExtended = "androidx.compose.material:material-icons-extended:$materialIconsExtended"

    //Material 3
    const val material3Version = "1.0.1"
    const val composeSizeClass = "androidx.compose.material3:material3-window-size-class:$material3Version"
    const val composeMaterial = "androidx.compose.material3:material3:$material3Version"

    private const val composeNavigationVersion = "2.5.3"
    const val composeNavigation = "androidx.navigation:navigation-compose:$composeNavigationVersion"

    private const val coilComposeVersion = "2.1.0"
    const val coilCompose = "io.coil-kt:coil-compose:$coilComposeVersion"

    // KOTLIN DATE TIME
    private const val dateTimeVersion = "0.4.0"
    const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"

    // KTOR
    private const val ktorVersion = "2.1.3"
    const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
    const val ktorSerialization = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val ktorAndroid = "io.ktor:ktor-client-android:$ktorVersion"
    const val ktorIOS = "io.ktor:ktor-client-ios:$ktorVersion"

    // GRADLE PLUGINS
    const val kotlinVersion = "1.7.20"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    const val gradleVersion = "7.3.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$gradleVersion"

    // TESTING
    private const val assertKVersion = "0.25"
    const val assertK = "com.willowtreeapps.assertk:assertk:$assertKVersion"

    private const val turbineVersion = "0.7.0"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"

    private const val jUnitVersion = "4.13.2"
    const val jUnit = "junit:junit:$jUnitVersion"

    private const val testRunnerVersion = "1.5.1"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"

    const val composeTesting = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"

    //Koin
    private const val koinVersion = "3.2.2"
    private const val koinComposeVersion = "3.2.1"
    const val koinCore = "io.insert-koin:koin-core:$koinVersion"
    const val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:$koinComposeVersion"

    //Moko MVVM
    private const val mokoMvvm = "0.14.0"
    const val mokoMvvmCore = "dev.icerock.moko:mvvm-core:$mokoMvvm"
    const val mokoMvvmFlow = "dev.icerock.moko:mvvm-flow:$mokoMvvm"

    private const val coroutinesNativeMtVersion = "1.6.0-native-mt"
    const val coroutinesNativeMt = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesNativeMtVersion"

    //Realm
    private const val realm = "1.2.0"
    const val realmBase = "io.realm.kotlin:library-base:$realm"
}