package com.famas.buddies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform