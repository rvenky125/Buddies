package com.famas.buddies.feature_select_map.interactors

import com.famas.buddies.feature_select_map.domain.model.PlaceToShow
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
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
                    val result = mapRepository.getPlace(event.lat, event.lng)
                    _state.value = state.value.copy(
                        placeToShow = result.data ?: PlaceToShow("${result.message}", "")
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
                val selectedPlace = state.value.places[event.placeIndex ?: return]
                _state.value =
                    state.value.copy(
                        selectedPlace = selectedPlace,
                        queryValue = selectedPlace.formattedAddress
                    )
            }
        }
    }
}