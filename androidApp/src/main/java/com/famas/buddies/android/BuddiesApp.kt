package com.famas.buddies.android

import android.app.Application
import com.famas.buddies.di.initKoin

class BuddiesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}