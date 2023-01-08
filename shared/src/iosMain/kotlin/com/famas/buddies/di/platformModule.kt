package com.famas.buddies.di

import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyVM
import com.famas.buddies.feature_add_buddy.select_location_map.interactors.SelectLocationVM
import com.famas.buddies.feature_feed.interactors.FeedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual val platformModule = module {
    factory {
        AddBuddyVM(get())
    }

    factory {
        SelectLocationVM(get())
    }

    factory {
        FeedViewModel(get())
    }
}

object ViewModels : KoinComponent {
    val addBuddyViewModel: AddBuddyVM = get()
    val selectLocationVM: SelectLocationVM = get()
    val feedViewModel: FeedViewModel = get()
}