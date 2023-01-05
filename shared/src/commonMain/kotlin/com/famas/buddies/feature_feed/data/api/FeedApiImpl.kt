package com.famas.buddies.feature_feed.data.api

import com.famas.buddies.feature_feed.domain.model.BuddyDto
import com.famas.buddies.util.BasicApiResponse
import com.famas.buddies.util.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class FeedApiImpl(private val httpClient: HttpClient): FeedApi {
    override suspend fun getBuddies(): BasicApiResponse<List<BuddyDto>> {
        return httpClient.get("${Constants.BASE_URL}buddy/get").body()
    }
}