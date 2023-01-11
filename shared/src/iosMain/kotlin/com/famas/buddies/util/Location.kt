package com.famas.buddies.util

import kotlinx.coroutines.runBlocking
import platform.CoreLocation.CLLocationManager

fun getLocation() {
    val locationManager = CLLocationManager()
    runBlocking {
        locationManager.location
    }
}