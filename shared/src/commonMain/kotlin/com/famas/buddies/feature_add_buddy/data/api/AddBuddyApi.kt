package com.famas.buddies.feature_add_buddy.data.api

import com.famas.buddies.feature_add_buddy.data.request.BuddyDto

interface AddBuddyApi {
    suspend fun addBuddy(buddy: BuddyDto)
}