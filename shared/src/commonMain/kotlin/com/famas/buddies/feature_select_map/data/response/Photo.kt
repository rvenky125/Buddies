package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("height")
    val height: Int,
    @SerialName("html_attributions")
    val htmlAttributions: List<String>,
    @SerialName("photo_reference")
    val photoReference: String,
    @SerialName("width")
    val width: Int
)