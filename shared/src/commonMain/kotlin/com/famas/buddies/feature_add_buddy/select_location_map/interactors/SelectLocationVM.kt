package com.famas.buddies.feature_add_buddy.select_location_map.interactors


import com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace.Candidate
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.PlaceToShow
import com.famas.buddies.feature_add_buddy.select_location_map.domain.repository.MapRepository
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SelectLocationVM(
    private val mapRepository: MapRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SelectLocationState())
    val state: CStateFlow<SelectLocationState> = _state.cStateFlow()

    fun onEvent(event: SelectLocationEvent) {
        when (event) {
            is SelectLocationEvent.SetLoading -> {
                _state.value = state.value.copy(loading = event.isLoading)
            }
            is SelectLocationEvent.OnChangeLocation -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(loading = true)
                    val result =
                        mapRepository.getPlace(event.location.latitude, event.location.longitude)
                    _state.value = state.value.copy(loading = false)

                    _state.value = state.value.copy(
                        placeToShow = result.data ?: PlaceToShow("${result.message}", "address")
                    )
                }
            }
            is SelectLocationEvent.OnQueryChange -> {
                _state.value = state.value.copy(queryValue = event.query)
                viewModelScope.launch {
                    val result = mapRepository.findPlaceFromQuery(event.query)
                    _state.value = state.value.copy(
                        places = result.data ?: emptyList()
                    )
                }
            }
            is SelectLocationEvent.OnSelectPlace -> {
                var selectedPlace: Candidate? = null
                if (event.placeIndex != null) {
                    selectedPlace = state.value.places[event.placeIndex]
                }
                _state.value =
                    state.value.copy(
                        selectedPlace = selectedPlace,
                        queryValue = selectedPlace?.name ?: ""
                    )
            }
        }
    }
}