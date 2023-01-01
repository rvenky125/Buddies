plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version Deps.kotlinVersion
    id("com.google.devtools.ksp") version "1.7.20-1.0.8"
}

android {
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
    namespace = "com.famas.buddies.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.famas.buddies.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "${Deps.composeVersion}"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Deps.composeUi)
    implementation(Deps.composeUiTooling)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeSizeClass)
    implementation(Deps.activityCompose)
    implementation(Deps.composeIconsExtended)
    implementation(Deps.composeNavigation)
    implementation(Deps.coilCompose)
    implementation(Accompanist.pager)
    implementation(Accompanist.placeholder)
    implementation(Accompanist.flowLayout)
    implementation(Accompanist.permissions)

    implementation(Deps.mokoMvvmFlow)
    implementation(Deps.koinCompose)

    implementation(Destinations.animations_core)
    ksp(Destinations.ksp)

    implementation(Maps.mapsCompose)
    implementation(Maps.playServicesMaps)
}