package com.famas.buddies.di

import com.famas.buddies.feature_add_buddy.data.api.AddBuddyApi
import com.famas.buddies.feature_add_buddy.data.api.AddBuddyApiImpl
import com.famas.buddies.feature_add_buddy.data.repository.AddBuddyRepoImpl
import com.famas.buddies.feature_add_buddy.domain.repository.AddBuddyRepository
import com.famas.buddies.feature_select_map.data.api.MapApi
import com.famas.buddies.feature_select_map.data.api.MapApiImpl
import com.famas.buddies.feature_select_map.data.repository.MapRepoImpl
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val mainModule = module {
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

    single<AddBuddyApi> {
        AddBuddyApiImpl(get())
    }

    single<AddBuddyRepository> {
        AddBuddyRepoImpl(get())
    }

    single<MapApi> {
        MapApiImpl(get())
    }

    single<MapRepository> {
        MapRepoImpl(get())
    }
}