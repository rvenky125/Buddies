package com.famas.buddies.di

import com.famas.buddies.feature_add_buddy.interactors.AddBuddyVM
import com.famas.buddies.feature_select_map.interactors.SelectLocationVM
import org.koin.dsl.module

actual val platformModule = module {
    factory {
        AddBuddyVM(get())
    }

    factory {
        SelectLocationVM(get())
    }
}

//object ViewModels : KoinComponent {
//    fun noteViewModel(): NoteViewModel = get()
//}