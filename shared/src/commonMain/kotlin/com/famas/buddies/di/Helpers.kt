package com.famas.buddies.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(getAllModules())
    }
}

fun getAllModules() = listOf(mainModule, platformModule)