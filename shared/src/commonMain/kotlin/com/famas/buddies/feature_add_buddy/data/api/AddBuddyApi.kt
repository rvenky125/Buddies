package com.famas.buddies.feature_add_buddy.data.api

import com.famas.buddies.feature_add_buddy.data.request.PostBuddyRequest
import com.famas.buddies.util.BasicApiResponse
import com.famas.buddies.util.ImageFile

interface AddBuddyApi {
    suspend fun addBuddy(buddy: PostBuddyRequest, files: List<ImageFile>): BasicApiResponse<Unit>
}