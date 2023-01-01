package com.famas.buddies.feature_select_map.data.response.findplace


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FindPlaceResponse(
    @SerialName("candidates")
    val candidates: List<Candidate>,
    @SerialName("status")
    val status: String
)