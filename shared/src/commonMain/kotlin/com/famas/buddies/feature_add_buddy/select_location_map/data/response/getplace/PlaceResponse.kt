package com.famas.buddies.feature_add_buddy.select_location_map.data.response.getplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    @SerialName("results")
    val results: List<Result>
)