package com.famas.buddies.feature_add_buddy.add_buddy_details.interactors

import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.LocationModel
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.PlaceToShow
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class AddBuddyNavArgs(
    @SerialName("location")
    val location: LocationModel,
    @SerialName("place")
    val place: PlaceToShow
)