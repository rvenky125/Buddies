package com.famas.buddies.feature_add_buddy.data.request

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class BuddyDto(
    @SerialName("name")
    val name: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double,
    @SerialName("note")
    val note: String,
    @SerialName("files")
    val files: List<String>,
    @SerialName("created_date_time")
    val createdDateTimestamp: Long
)