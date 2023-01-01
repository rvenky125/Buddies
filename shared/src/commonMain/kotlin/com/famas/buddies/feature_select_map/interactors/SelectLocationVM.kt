package com.famas.buddies.feature_select_map.interactors

import com.famas.buddies.feature_add_buddy.interactors.AddBuddyEvent
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SelectLocationVM(
    private val mapRepository: MapRepository
): ViewModel() {
    private val _state = MutableStateFlow(SelectLocationState())
    val state: CStateFlow<SelectLocationState> = _state.cStateFlow()


    fun onEvent(event: SelectLocationEvent) {
        when (event) {
            is SelectLocationEvent.SetLoading -> {
                _state.value = state.value.copy(loading = event.isLoading)
            }

        }
    }

    fun fetchForLocation() {
        viewModelScope.launch {
            print("get place called" )
            val result = mapRepository.getPlace(17.90829064445241, 83.1857109491208)
            print(result.data)
        }
    }

    init {
        viewModelScope.launch {
            print("get place called" )
            val result = mapRepository.getPlace(17.90829064445241, 83.1857109491208)
            print(result.data)
        }
    }
}