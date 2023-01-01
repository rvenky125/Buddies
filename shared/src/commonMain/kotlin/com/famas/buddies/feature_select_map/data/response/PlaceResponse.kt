package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    @SerialName("html_attributions")
    val htmlAttributions: List<String>,
    @SerialName("result")
    val result: Result,
    @SerialName("status")
    val status: String
)