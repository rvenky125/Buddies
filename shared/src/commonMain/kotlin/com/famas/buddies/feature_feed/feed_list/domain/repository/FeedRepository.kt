package com.famas.buddies.feature_feed.feed_list.domain.repository

import com.famas.buddies.feature_feed.feed_list.domain.model.Buddy
import com.famas.buddies.feature_feed.feed_list.domain.model.BuddyDto
import com.famas.buddies.util.Response

interface FeedRepository {
    suspend fun getBuddies(): Response<List<Buddy>>
}