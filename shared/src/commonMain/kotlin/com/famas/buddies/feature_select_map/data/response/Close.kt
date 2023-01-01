package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Close(
    @SerialName("day")
    val day: Int,
    @SerialName("time")
    val time: String
)