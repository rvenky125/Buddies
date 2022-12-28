plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("io.realm.kotlin")
    kotlin("plugin.serialization") version Deps.kotlinVersion
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
            export(Deps.mokoMvvmCore)
            export(Deps.mokoMvvmFlow)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.coroutinesNativeMt)
                implementation(Deps.realmBase)
                api(Deps.koinCore)
                implementation(Deps.ktorCore)
                implementation(Deps.ktorSerialization)
                implementation(Deps.ktorSerializationJson)
                api(Deps.mokoMvvmCore)
                api(Deps.mokoMvvmFlow)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(Deps.koinAndroid)
                implementation(Deps.ktorAndroid)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(Deps.ktorIOS)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.famas.buddies"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
//        targetSdk = 33
    }
}