package com.famas.buddies.feature_add_buddy.add_buddy_details.interactors

import com.famas.buddies.feature_add_buddy.add_buddy_details.data.request.PostBuddyRequest
import com.famas.buddies.feature_add_buddy.add_buddy_details.domain.repository.AddBuddyRepository
import com.famas.buddies.util.Response
import com.famas.buddies.util.UiEvent
import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddBuddyVM(
    private val addBuddyRepository: AddBuddyRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AddBuddyState())
    val state: CStateFlow<AddBuddyState> = _state.cStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: CFlow<UiEvent> = _uiEvent.asSharedFlow().cFlow()

    private var addBuddyNavArgs: AddBuddyNavArgs? = null

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
            is AddBuddyEvent.OnAgeChange -> {
                _state.value = state.value.copy(
                    age = event.age
                )
            }
            is AddBuddyEvent.OnGenderChange -> {
                _state.value = state.value.copy(
                    gender = event.gender
                )
            }
            AddBuddyEvent.OnSubmit -> {
                onSubmit()
            }
        }
    }

    fun setPickedLocation(navArgs: AddBuddyNavArgs) {
        addBuddyNavArgs = navArgs
    }

    private fun onSubmit() {
        viewModelScope.launch {
            if (_state.value.age.toFloatOrNull() == null) {
                _uiEvent.emit(UiEvent.ShowMessage("Please provide proper age"))
                return@launch
            }

            if (addBuddyNavArgs == null || _state.value.name.isEmpty()) {
                _uiEvent.emit(UiEvent.ShowMessage("Please provide all fields"))
                return@launch
            }

            _state.value = state.value.copy(loading = true)
            val response = addBuddyRepository.addBuddy(
                PostBuddyRequest(
                    buddy_name = _state.value.name,
                    lat = addBuddyNavArgs?.location?.latitude ?: return@launch,
                    lng = addBuddyNavArgs?.location?.longitude ?: return@launch,
                    note = _state.value.note,
                    age = _state.value.age.toFloatOrNull() ?: return@launch,
                    gender = _state.value.gender?.id ?: return@launch,
                    address = addBuddyNavArgs?.place?.address ?: return@launch
                ),
                _state.value.files
            )
            _state.value = state.value.copy(loading = false)

            when (response) {
                is Response.Success -> {
                    _uiEvent.emit(UiEvent.ShowMessage("Buddy added successfully"))
                    _uiEvent.emit(UiEvent.GoBack)
                }
                is Response.Failure -> {
                    _uiEvent.emit(UiEvent.ShowMessage("Failed to add buddy"))
                }
            }
        }
    }
}