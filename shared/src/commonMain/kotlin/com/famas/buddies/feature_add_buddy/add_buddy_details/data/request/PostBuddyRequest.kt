package com.famas.buddies.feature_add_buddy.add_buddy_details.data.request

@kotlinx.serialization.Serializable
data class PostBuddyRequest(
    val buddy_name: String,
    val note: String,
    val lat: Double,
    val lng: Double,
    val age: Int,
    val gender: Char
)
