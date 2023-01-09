package com.famas.buddies.feature_feed.data.api

import com.famas.buddies.feature_feed.feed_list.domain.model.BuddyDto
import com.famas.buddies.util.BasicApiResponse

interface FeedApi {
    suspend fun getBuddies(): BasicApiResponse<List<BuddyDto>>
}