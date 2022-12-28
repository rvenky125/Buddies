package com.famas.buddies.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule = module {
    single {
//        val config = RealmConfiguration.Builder(schema = setOf(NoteObject::class))
//            .build()
//        Realm.open(config)
    }

//    single<NoteDataSource> {
//        NoteDataSourceImpl(get())
//    }
}