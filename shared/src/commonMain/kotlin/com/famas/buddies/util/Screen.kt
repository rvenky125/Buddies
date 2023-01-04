package com.famas.buddies.util

sealed class Screen {
    object Feed : Screen()
    object AddBuddy : Screen()
}
