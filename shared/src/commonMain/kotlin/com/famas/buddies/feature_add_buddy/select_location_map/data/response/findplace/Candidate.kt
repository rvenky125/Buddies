package com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    @SerialName("formatted_address")
    val formattedAddress: String,
    @SerialName("geometry")
    val geometry: Geometry,
    @SerialName("name")
    val name: String,
    @SerialName("opening_hours")
    val openingHours: OpeningHours,
    @SerialName("rating")
    val rating: Double
)