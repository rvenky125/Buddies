package com.famas.buddies.feature_feed.data.repository

import com.famas.buddies.feature_feed.data.api.FeedApi
import com.famas.buddies.feature_feed.domain.model.BuddyDto
import com.famas.buddies.feature_feed.domain.repository.FeedRepository
import com.famas.buddies.util.BasicApiResponse
import com.famas.buddies.util.Response

class FeedRepositoryImpl(
    private val feedApi: FeedApi
): FeedRepository {
    override suspend fun getBuddies(): Response<List<BuddyDto>> {
        return try {
            val response = feedApi.getBuddies()
            if (response.successful) {
                Response.Success(data = response.data)
            } else {
                Response.Failure(response.message ?: "Something went wrong")
            }
        } catch (e: Exception) {
            Response.Failure("Something went wrong")
        }
    }
}