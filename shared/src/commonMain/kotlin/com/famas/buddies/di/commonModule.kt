package com.famas.buddies.di

import com.famas.buddies.feature_select_map.data.api.MapApi
import com.famas.buddies.feature_select_map.data.api.MapApiImpl
import com.famas.buddies.feature_select_map.data.repository.MapRepoImpl
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
import io.ktor.client.*
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

    single {
        HttpClient() {

        }
    }

    single<MapApi> {
        MapApiImpl(get())
    }

    single<MapRepository> {
        MapRepoImpl(get())
    }
}