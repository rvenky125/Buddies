package com.famas.buddies.feature_add_buddy.data.repository

import com.famas.buddies.feature_add_buddy.data.api.AddBuddyApi
import com.famas.buddies.feature_add_buddy.data.request.BuddyDto
import com.famas.buddies.feature_add_buddy.domain.repository.AddBuddyRepository
import com.famas.buddies.util.Response

class AddBuddyRepoImpl(private val addBuddyApi: AddBuddyApi) : AddBuddyRepository {
    override suspend fun addBuddy(buddy: BuddyDto): Response<Unit> {
        return try {
            addBuddyApi.addBuddy(buddy)
            Response.Success()
        } catch (e: Exception) {
            Response.Failure("Something went wrong")
        }
    }
}