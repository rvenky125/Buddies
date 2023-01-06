package com.famas.buddies.di

import com.famas.buddies.feature_add_buddy.add_buddy_details.data.api.AddBuddyApi
import com.famas.buddies.feature_add_buddy.add_buddy_details.data.api.AddBuddyApiImpl
import com.famas.buddies.feature_add_buddy.add_buddy_details.data.repository.AddBuddyRepoImpl
import com.famas.buddies.feature_add_buddy.add_buddy_details.domain.repository.AddBuddyRepository
import com.famas.buddies.feature_add_buddy.select_location_map.data.repository.MapRepoImpl
import com.famas.buddies.feature_add_buddy.select_location_map.domain.repository.MapRepository
import com.famas.buddies.feature_feed.data.api.FeedApi
import com.famas.buddies.feature_feed.data.api.FeedApiImpl
import com.famas.buddies.feature_feed.data.repository.FeedRepositoryImpl
import com.famas.buddies.feature_feed.domain.repository.FeedRepository
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

    single<com.famas.buddies.feature_add_buddy.select_location_map.data.api.MapApi> {
        com.famas.buddies.feature_add_buddy.select_location_map.data.api.MapApiImpl(get())
    }

    single<MapRepository> {
        MapRepoImpl(get())
    }

    single<FeedApi> {
        FeedApiImpl(get())
    }

    single<FeedRepository> {
        FeedRepositoryImpl(get())
    }
}