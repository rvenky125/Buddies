package com.famas.buddies.feature_select_map.data.response.getplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    @SerialName("results")
    val results: List<Result>
)