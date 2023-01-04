package com.famas.buddies.feature_add_buddy.data.api

import com.famas.buddies.feature_add_buddy.data.request.BuddyDto
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class AddBuddyApiImpl : AddBuddyApi {
    val firestore = Firebase.firestore
    val buddiesCollection = firestore.collection("buddies")

    override suspend fun addBuddy(buddy: BuddyDto) {
        buddiesCollection.add(BuddyDto.serializer(), buddy, true)
    }
}