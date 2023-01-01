package com.famas.buddies.feature_select_map.interactors

sealed class SelectLocationEvent {
    data class SetLoading(val isLoading: Boolean): SelectLocationEvent()

}