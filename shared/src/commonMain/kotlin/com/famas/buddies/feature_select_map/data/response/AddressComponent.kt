package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressComponent(
    @SerialName("long_name")
    val longName: String,
    @SerialName("short_name")
    val shortName: String,
    @SerialName("types")
    val types: List<String>
)