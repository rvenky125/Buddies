package com.famas.buddies.feature_feed.feed_list.domain.model

import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.Gender

@kotlinx.serialization.Serializable
data class BuddyDto(
    val name: String,
    val note: String,
    val files: List<String>,
    val lat: Double,
    val lng: Double,
    val gender: Char,
    val age: Float,
    val created_at: Long,
    val address: String = "",
    val id: String,
) {
    fun toBuddy(): Buddy {
        return Buddy(
            name = name,
            note = note,
            files = files,
            lat = lat,
            lng = lng,
            gender = Gender.values().find { it.id == gender }?.name ?: "N/A",
            age = age,
            created_at = created_at,
            address = address,
            id = id,
        )
    }
}
