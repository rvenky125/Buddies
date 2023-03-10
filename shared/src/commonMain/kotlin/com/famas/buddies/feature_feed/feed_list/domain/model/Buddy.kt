package com.famas.buddies.feature_feed.feed_list.domain.model

@kotlinx.serialization.Serializable
data class Buddy(
    val name: String,
    val note: String,
    val files: List<String>,
    val lat: Double,
    val lng: Double,
    val gender: String,
    val age: Float,
    val created_at: Long,
    val address: String = "",
    val id: String,
)
