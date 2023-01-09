package com.famas.buddies.feature_add_buddy.select_location_map.domain.model

@kotlinx.serialization.Serializable
data class PlaceToShow(
    val name: String,
    val address: String
)