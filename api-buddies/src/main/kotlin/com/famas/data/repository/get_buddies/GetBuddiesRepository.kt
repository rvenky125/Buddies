package com.famas.data.repository.get_buddies

import com.famas.data.models.Buddy

interface GetBuddiesRepository {
    suspend fun getBuddies(): List<Buddy>
}