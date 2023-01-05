package com.famas.di

import com.famas.data.repository.post_buddies.PostBuddyRepoImpl
import com.famas.data.repository.post_buddies.PostBuddyRepository
import com.famas.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module { 
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }

    single<PostBuddyRepository> {
        PostBuddyRepoImpl(get())
    }
}