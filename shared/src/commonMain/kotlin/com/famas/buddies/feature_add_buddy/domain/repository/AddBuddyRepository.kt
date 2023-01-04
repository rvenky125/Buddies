package com.famas.buddies.feature_add_buddy.domain.repository

import com.famas.buddies.feature_add_buddy.data.request.BuddyDto
import com.famas.buddies.util.Response

interface AddBuddyRepository {
    suspend fun addBuddy(buddy: BuddyDto): Response<Unit>
}