package com.famas.buddies.di

import android.content.Context
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyVM
import com.famas.buddies.feature_add_buddy.select_location_map.interactors.SelectLocationVM
import com.famas.buddies.feature_feed.feed_list.interactors.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val platformModule = module {
    viewModel {
        AddBuddyVM(get())
    }

    viewModel {
        SelectLocationVM(get())
    }

    viewModel {
        FeedViewModel(get())
    }
}

fun initFirebase(context: Context) {
}