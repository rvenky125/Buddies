package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerialName("location")
    val location: Location,
    @SerialName("viewport")
    val viewport: Viewport
)