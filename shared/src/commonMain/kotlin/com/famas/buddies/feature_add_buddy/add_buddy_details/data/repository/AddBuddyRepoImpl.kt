package com.famas.buddies.feature_add_buddy.add_buddy_details.data.repository

import com.famas.buddies.feature_add_buddy.add_buddy_details.data.api.AddBuddyApi
import com.famas.buddies.feature_add_buddy.add_buddy_details.data.request.PostBuddyRequest
import com.famas.buddies.feature_add_buddy.add_buddy_details.domain.repository.AddBuddyRepository
import com.famas.buddies.util.ImageFile
import com.famas.buddies.util.Response

class AddBuddyRepoImpl(private val addBuddyApi: AddBuddyApi) : AddBuddyRepository {
    override suspend fun addBuddy(buddy: PostBuddyRequest, files: List<ImageFile>): Response<Unit> {
        return try {
            val response = addBuddyApi.addBuddy(buddy, files)
            if (response.successful) {
                Response.Success()
            } else Response.Failure(response.message ?: "Unknown error occurred")
        } catch (e: Exception) {
            Response.Failure("Something went wrong")
        }
    }
}