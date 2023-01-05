package com.famas.data.repository.post_buddies

import com.famas.data.models.Buddy

interface PostBuddyRepository {
    suspend fun postBuddy(buddy: Buddy): Boolean
}