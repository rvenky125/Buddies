package com.famas.data.repository.get_buddies

import com.famas.data.models.Buddy
import org.litote.kmongo.coroutine.CoroutineDatabase

class GetBuddyRepoImpl(
    db: CoroutineDatabase
) : GetBuddiesRepository {
    val buddies = db.getCollection<Buddy>()
    override suspend fun getBuddies(): List<Buddy> {
        return buddies
            .find().toList().asReversed()
    }
}