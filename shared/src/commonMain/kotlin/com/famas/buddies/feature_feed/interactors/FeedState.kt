package com.famas.buddies.feature_feed.interactors

import com.famas.buddies.feature_feed.domain.model.BuddyDto

data class FeedState(
    val loading: Boolean = false,
    val buddies: List<BuddyDto> = emptyList(),
)
