package com.famas.buddies.feature_add_buddy.select_location_map.data.response.getplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Southwest(
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double
)