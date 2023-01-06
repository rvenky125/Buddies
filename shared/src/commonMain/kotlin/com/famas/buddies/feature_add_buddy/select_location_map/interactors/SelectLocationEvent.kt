package com.famas.buddies.feature_add_buddy.select_location_map.interactors

import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.LocationModel


sealed class SelectLocationEvent {
    data class SetLoading(val isLoading: Boolean) : SelectLocationEvent()
    data class OnChangeLocation(val location: LocationModel) : SelectLocationEvent()
    data class OnSelectPlace(val placeIndex: Int?) : SelectLocationEvent()
    data class OnQueryChange(val query: String) : SelectLocationEvent()
}