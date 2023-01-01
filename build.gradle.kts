plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Deps.gradleVersion).apply(false)
    id("com.android.library").version(Deps.gradleVersion).apply(false)
    kotlin("android").version(Deps.kotlinVersion).apply(false)
    kotlin("multiplatform").version(Deps.kotlinVersion).apply(false)

    //Add this for realmDB
    id("io.realm.kotlin").version("1.3.0").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
