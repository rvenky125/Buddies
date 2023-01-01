package com.famas.buddies.feature_select_map.interactors

import com.famas.buddies.feature_select_map.data.response.findplace.Candidate

sealed class SelectLocationEvent {
    data class SetLoading(val isLoading: Boolean) : SelectLocationEvent()
    data class OnChangeLocation(val lat: Double, val lng: Double) : SelectLocationEvent()
    data class OnSelectPlace(val placeIndex: Int?) : SelectLocationEvent()
    data class OnQueryChange(val query: String) : SelectLocationEvent()
}