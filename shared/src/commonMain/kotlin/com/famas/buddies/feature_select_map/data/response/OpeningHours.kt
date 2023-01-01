package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpeningHours(
    @SerialName("open_now")
    val openNow: Boolean,
    @SerialName("periods")
    val periods: List<Period>,
    @SerialName("weekday_text")
    val weekdayText: List<String>
)