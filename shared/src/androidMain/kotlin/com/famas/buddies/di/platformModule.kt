package com.famas.buddies.di

import com.famas.buddies.interactors.screen_add_buddy.AddBuddyVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val platformModule = module {
    viewModel {
        AddBuddyVM()
    }
}