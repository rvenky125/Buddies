package com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerialName("location")
    val location: Location,
    @SerialName("viewport")
    val viewport: Viewport
)