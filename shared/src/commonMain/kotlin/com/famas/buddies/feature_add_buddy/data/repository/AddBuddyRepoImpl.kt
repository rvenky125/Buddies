package com.famas.buddies.feature_add_buddy.data.repository

import com.famas.buddies.feature_add_buddy.data.api.AddBuddyApi
import com.famas.buddies.feature_add_buddy.data.request.PostBuddyRequest
import com.famas.buddies.feature_add_buddy.domain.repository.AddBuddyRepository
import com.famas.buddies.util.ImageFile
import com.famas.buddies.util.Response
import com.famas.buddies.util.toByteArray

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