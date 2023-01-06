package com.famas.buddies.feature_add_buddy.add_buddy_details.domain.repository

import com.famas.buddies.feature_add_buddy.add_buddy_details.data.request.PostBuddyRequest
import com.famas.buddies.util.ImageFile
import com.famas.buddies.util.Response

interface AddBuddyRepository {
    suspend fun addBuddy(buddy: PostBuddyRequest, files: List<ImageFile>): Response<Unit>
}