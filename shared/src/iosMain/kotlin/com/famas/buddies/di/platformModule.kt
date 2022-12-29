package com.famas.buddies.di

import com.famas.buddies.interactors.screen_add_buddy.AddBuddyVM
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual val platformModule = module {
    factory {
        AddBuddyVM()
    }
}

//object ViewModels : KoinComponent {
//    fun noteViewModel(): NoteViewModel = get()
//}