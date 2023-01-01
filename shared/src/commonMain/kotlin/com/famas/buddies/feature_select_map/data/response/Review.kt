package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("author_name")
    val authorName: String,
    @SerialName("author_url")
    val authorUrl: String,
    @SerialName("language")
    val language: String,
    @SerialName("profile_photo_url")
    val profilePhotoUrl: String,
    @SerialName("rating")
    val rating: Int,
    @SerialName("relative_time_description")
    val relativeTimeDescription: String,
    @SerialName("text")
    val text: String,
    @SerialName("time")
    val time: Int
)