package com.famas.buddies.feature_feed.feed_list.interactors

import com.famas.buddies.feature_feed.feed_list.domain.model.Buddy
import com.famas.buddies.feature_feed.feed_list.domain.model.BuddyDto

data class FeedState(
    val loading: Boolean = false,
    val buddies: List<Buddy> = emptyList(),
)
