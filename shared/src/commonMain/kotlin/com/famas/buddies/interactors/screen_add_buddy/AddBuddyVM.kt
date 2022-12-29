package com.famas.buddies.interactors.screen_add_buddy

import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddBuddyVM : ViewModel() {
    private val _state = MutableStateFlow(AddBuddyState())
    val state: CStateFlow<AddBuddyState> = _state.cStateFlow()


    fun onEvent(event: AddBuddyEvent) {
        when (event) {
            is AddBuddyEvent.OnNameChange -> {
                _state.value = state.value.copy(name = event.text)
            }
            is AddBuddyEvent.OnAddFiles -> {
                _state.value = state.value.copy(files = state.value.files.plus(event.files))
            }
            is AddBuddyEvent.OnRemoveFile -> {
                _state.value =
                    state.value.copy(files = state.value.files.minus(state.value.files[event.index]))
            }
            is AddBuddyEvent.SetLoading -> {
                _state.value = state.value.copy(loading = event.isLoading)
            }
            is AddBuddyEvent.OnNoteChange -> {
                _state.value = state.value.copy(note = event.text)
            }
            is AddBuddyEvent.OnChangeShowMap -> {
                _state.value = state.value.copy(showMap = event.show)
            }
        }
    }
}