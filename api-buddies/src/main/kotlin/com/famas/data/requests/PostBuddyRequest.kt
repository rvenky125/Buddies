package com.famas.data.requests

@kotlinx.serialization.Serializable
data class PostBuddyRequest(
    val buddy_name: String,
    val note: String,
    val lat: Double,
    val lng: Double,
    val age: Float,
    val gender: Char,
    val address: String
)
