package com.famas.buddies.feature_feed.domain.repository

import com.famas.buddies.feature_feed.domain.model.BuddyDto
import com.famas.buddies.util.Response

interface FeedRepository {
    suspend fun getBuddies(): Response<List<BuddyDto>>
}