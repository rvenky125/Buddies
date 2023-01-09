package com.famas.buddies.feature_feed.interactors

import com.famas.buddies.feature_feed.domain.repository.FeedRepository
import com.famas.buddies.util.Response
import com.famas.buddies.util.UiEvent
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: FeedRepository
): ViewModel() {
    private val _state = MutableStateFlow(FeedState())
    val state: CStateFlow<FeedState> = _state.cStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow().cFlow()

    fun syncBuddies() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                loading = true
            )
            val response = repository.getBuddies()
            _state.value = state.value.copy(
                loading = false
            )
            when (response) {
                is Response.Success -> {
                    _state.value = state.value.copy(
                        buddies = response.data ?: emptyList()
                    )
                }
                is Response.Failure -> {
                    _uiEvent.emit(UiEvent.ShowMessage(response.message ?: "Failed to load messages"))
                }
            }
        }
    }

    init {
        syncBuddies()
    }
}