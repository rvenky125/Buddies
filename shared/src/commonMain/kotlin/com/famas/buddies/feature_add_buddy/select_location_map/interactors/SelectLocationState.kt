package com.famas.buddies.feature_add_buddy.select_location_map.interactors

import com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace.Candidate
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.PlaceToShow

data class SelectLocationState(
    val loading: Boolean = false,
    val queryValue: String = "",
    val placeToShow: PlaceToShow = PlaceToShow("", ""),
    val places: List<Candidate> = emptyList(),
    val selectedPlace: Candidate? = null
)