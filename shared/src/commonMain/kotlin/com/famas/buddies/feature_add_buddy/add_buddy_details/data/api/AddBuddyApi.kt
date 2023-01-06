package com.famas.buddies.feature_add_buddy.add_buddy_details.data.api

import com.famas.buddies.feature_add_buddy.add_buddy_details.data.request.PostBuddyRequest
import com.famas.buddies.util.BasicApiResponse
import com.famas.buddies.util.ImageFile

interface AddBuddyApi {
    suspend fun addBuddy(buddy: PostBuddyRequest, files: List<ImageFile>): BasicApiResponse<Unit>
}