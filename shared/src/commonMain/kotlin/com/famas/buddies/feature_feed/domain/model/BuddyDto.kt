package com.famas.buddies.feature_feed.domain.model

@kotlinx.serialization.Serializable
data class BuddyDto(
    val name: String,
    val note: String,
    val files: List<String>,
    val lat: Double,
    val lng: Double,
    val created_at: Long,
    val id: String
)
