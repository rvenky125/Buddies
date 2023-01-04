package com.famas.buddies.di

import android.content.Context
import com.famas.buddies.feature_add_buddy.interactors.AddBuddyVM
import com.famas.buddies.feature_select_map.interactors.SelectLocationVM
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val platformModule = module {
    viewModel {
        AddBuddyVM(get())
    }

    viewModel {
        SelectLocationVM(get())
    }
}

fun initFirebase(context: Context) {
    Firebase.initialize(context = context)
}