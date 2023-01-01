package com.famas.buddies.di

import com.famas.buddies.feature_select_map.data.api.MapApi
import com.famas.buddies.feature_select_map.data.api.MapApiImpl
import com.famas.buddies.feature_select_map.data.repository.MapRepoImpl
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
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
            install(Logging)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<MapApi> {
        MapApiImpl(get())
    }

    single<MapRepository> {
        MapRepoImpl(get())
    }
}