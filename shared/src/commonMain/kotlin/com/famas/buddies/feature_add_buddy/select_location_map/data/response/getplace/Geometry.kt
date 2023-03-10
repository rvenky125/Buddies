package com.famas.buddies.feature_add_buddy.select_location_map.data.response.getplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerialName("location")
    val location: Location,
    @SerialName("location_type")
    val locationType: String,
    @SerialName("viewport")
    val viewport: Viewport
)