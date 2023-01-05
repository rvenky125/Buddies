package com.famas.data.repository.post_buddies

import com.famas.data.models.Buddy
import org.litote.kmongo.coroutine.CoroutineDatabase

class PostBuddyRepoImpl(
    db: CoroutineDatabase
): PostBuddyRepository {
    private val buddies = db.getCollection<Buddy>()

    override suspend fun postBuddy(buddy: Buddy): Boolean {
        return buddies.insertOne(buddy).wasAcknowledged()
    }
}