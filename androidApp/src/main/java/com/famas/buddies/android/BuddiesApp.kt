package com.famas.buddies.android

import android.app.Application
import com.famas.buddies.di.getAllModules
import com.famas.buddies.di.initFirebase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BuddiesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initFirebase(applicationContext)

        startKoin {
            androidContext(applicationContext)
            modules(getAllModules())
        }
    }
}